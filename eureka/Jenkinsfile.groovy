pipeline {
    agent any
    tools {
        gradle 'Gradle 8.5'
    }
    environment {
        DOCKER_IMAGE_NAME = 'joonseong/mancity-eureka'
        DOCKERFILE_PATH = './eureka/Dockerfile'
        CONTAINER_NAME = 'mancity-eureka'
        REGISTRY_CREDENTIAL = 'dockerhub_IdPwd'
        DOCKER_IMAGE = ''
        DOCKER_IMAGE_TAG = 'latest'
    }
    stages {
        stage('GitLab Clone') {
            steps {
                git branch : 'develop-be-eureka', credentialsId: 'gitlab_access_token', url: 'https://lab.ssafy.com/s10-ai-image-sub2/S10P22C201.git'
            }
        }
        stage('Gradle Build') {
            steps {
                echo 'Building..'
                dir('./eureka') {
                    sh 'chmod +x gradlew'
                    sh './gradlew clean bootjar'
                }
            }
        }
        stage('Docker Build Image') {
            steps {
                dir('./eureka') {
                    script {
                        DOCKER_IMAGE = docker.build("${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG}", "-f Dockerfile .")
                    }
                }
            }
        }
        stage('Push Image to DockerHub') {
            steps {
                script {
                    docker.withRegistry('', REGISTRY_CREDENTIAL) {
                        DOCKER_IMAGE.push()
                    }
                }
            }
        }

        stage('Delete Previous eureka Docker Container'){
            steps {
                script {
                    // 컨테이너가 실행중이 아니거나 중지되어 있는 경우 아무런 동작하지 않고 넘어가도록
                    sh "docker stop ${CONTAINER_NAME} || true"

                    def exitedContainers = sh(script: "docker ps --filter status=exited -q", returnStdout: true).trim()
                    if (exitedContainers) {
                        sh "docker rm ${exitedContainers}"
                    } else {
                        echo "No exited containers to remove."
                    }
                }
            }
        }

        stage('Prune Image'){
            steps {
                echo '##### delete <none> TAG images #####'
                script {
                    sh "docker image prune -f"
                }
            }
        }

        stage('Pull from DockerHub') {
            steps {
                script {
                    sh 'docker pull ${DOCKER_IMAGE_NAME}'
                }
            }
        }

        stage('Run Docker Container') {
            steps {
                script {
                    sh 'docker run -d --name ${CONTAINER_NAME} -p 8761:8761 ${DOCKER_IMAGE_NAME}'
                }
            }
        }
    }
}
