package com.mancity.user.user.domain;

import com.mancity.user.stat.domain.LastStat;
import com.mancity.user.stat.domain.MainStat;
import com.mancity.user.stat.domain.Stat;
import com.mancity.user.ClubMember.domain.ClubMember;
import com.mancity.user.user.application.dto.request.UpdateRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClubMember> clubMember = new ArrayList<>();

    private String nickName;

    private String email;

    private String password;

    private String salt;

    private String image;

    private int height;

    private int weight;

    private int foot;

    private boolean isPlayer;

    private String fcmToken;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn
    private Stat stat;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn
    private MainStat mainStat;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn
    private LastStat lastStat;

    public void updateStat(Stat stat) {
        this.stat = stat;
    }

    public void updateMainStat(MainStat mainStat) {
        this.mainStat = mainStat;
    }

    public void updateLastStat(LastStat lastStat) {
        this.lastStat = lastStat;
    }

    public void updateFcmToken(String token) {
        this.fcmToken = token;
    }

    public void update(UpdateRequestDto dto) {
        if (isValid(dto.getNickName())) {
            this.nickName = dto.getNickName();
        }

        if (isValid(dto.getFoot())) {
            this.foot = dto.getFoot();
        }

        if (isValid(dto.getHeight())) {
            this.height = dto.getHeight();
        }

        if (isValid(dto.getWeight())) {
            this.weight = dto.getWeight();
        }

        if (isValid(dto.getIsPlayer())) {
            this.isPlayer = dto.getIsPlayer();
        }
    }

    public void uploadImage(String url) {
        this.image = url;
    }

    private boolean isValid(Object obj) {
        return obj != null && !ObjectUtils.isEmpty(obj);
    }

}
