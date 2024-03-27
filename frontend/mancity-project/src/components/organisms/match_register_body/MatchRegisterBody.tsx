import WheelPicker from "@/components/atoms/time_picker/TimePicker";
import ReverseButton from "@/components/atoms/reverse_button/ReverseButton";
import Dropdown from "@/components/molecules/dropdown/Dropdown";
import { forwardRef, useEffect, useState } from "react";
import dayjs, { Dayjs } from "dayjs";
import DatePicker from "react-datepicker";

import "react-datepicker/dist/react-datepicker.css";
import Typography from "@/components/atoms/typography/Typography";
import SearchBar from "@/components/molecules/search_bar/SearchBar";

const MatchRegisterBody = () => {
  // 날짜 설정
  const [selectedDate, setSelectedDate] = useState<Date>(new Date());

  const ExampleCustomInput = forwardRef<
    HTMLButtonElement,
    ExampleCustomInputProps
  >(({ value, onClick }, ref) => (
    <button onClick={onClick} ref={ref}>
      <div className="flex justify-end">
        <div className="w-[85vw] max-w-[510px]   ">
          <div className=" mb-1">
            <span>{value}</span>
          </div>
        </div>
      </div>
    </button>
  ));

  // 시간 설정
  const [timeValue, setTimeValue] = useState<Dayjs | null>(
    dayjs("2022-04-17T15:30")
  );
  const hour = timeValue ? Number(timeValue.hour()) : 0;

  // 성별
  const GenderInfo = [
    { value: 1, label: "남성" },
    { value: 2, label: "여성" },
    { value: 3, label: "혼성" },
  ];
  const [genderLabel, setGenderLabel] = useState("성별");
  const [genderValue, setGenderValue] = useState(0);

  // 인원

  const RuleInfo = [
    { value: 1, label: "5vs5" },
    { value: 2, label: "6vs6" },
  ];
  const [ruleLabel, setRuleLabel] = useState("인원");
  const [ruleValue, setRuleValue] = useState(0);

  // 수준
  const LevelInfo = [
    { value: 1, label: "취미풋살" },
    { value: 2, label: "선출포함" },
    { value: 3, label: "프로풋살" },
  ];
  const [levelLabel, setLevelLabel] = useState("선출");
  const [levelValue, setLevelValue] = useState(0);

  const matchPlace: matchPlace[] = [
    { value: 1, label: "서울" },
    { value: 2, label: "경기" },
    { value: 3, label: "광주" },
    { value: 4, label: "대구" },
    { value: 5, label: "대전" },
    { value: 6, label: "인천" },
    { value: 7, label: "강원" },
    { value: 8, label: "경상" },
    { value: 9, label: "부산" },
    { value: 10, label: "세종" },
    { value: 11, label: "울산" },
    { value: 12, label: "전라" },
    { value: 13, label: "제주" },
    { value: 14, label: "충청" },
  ];

  // 유효성 검사 상태 추가
  const [isFormValid, setIsFormValid] = useState(false);

  useEffect(() => {
    // 모든 입력값의 유효성 검사
    const isValid = genderValue !== 0 && ruleValue !== 0 && levelValue !== 0;

    setIsFormValid(isValid);
  }, [genderValue, ruleValue, levelValue]);

  return (
    <div className="">
      {/* 날짜 */}
      <div className="m-4">
        <div className="mb-1">
          <Typography
            textSize="text-sm"
            fontWeight="font-medium"
            textColor="text-sofcity"
            label="날짜"
          />
        </div>
        <div className=" font-sm text-sofcity bottom-0 border-b-[0.08rem] border-sofcity">
          <DatePicker
            dateFormat="yyyy년 MM월 dd일"
            selected={selectedDate}
            onChange={(date: Date) => setSelectedDate(date)}
            customInput={<ExampleCustomInput />}
          />
        </div>
      </div>
      {/* 시간 */}
      <div>
        <WheelPicker timeValue={timeValue} setTimeValue={setTimeValue} />
      </div>
      {/* 경기장 */}
      <div className="mt-6 ml-4 mr-1">
        <div className="flex-row">
          <Typography
            textSize="text-sm"
            fontWeight="font-medium"
            textColor="text-sofcity"
            label="경기장"
          />
          <SearchBar contents={matchPlace} />
        </div>
      </div>
      {/* 인원 */}
      <div>
        <Dropdown
          typographyLabel="인원"
          items={RuleInfo}
          position={ruleLabel}
          setPosition={setRuleLabel}
          setNumberValue={setRuleValue}
        />
      </div>
      {/* 성별 */}

      <div className="w-full">
        <Dropdown
          typographyLabel="성별"
          items={GenderInfo}
          position={genderLabel}
          setPosition={setGenderLabel}
          setNumberValue={setGenderValue}
        />
      </div>

      <div>
        <Dropdown
          typographyLabel="선출"
          items={LevelInfo}
          position={levelLabel}
          setPosition={setLevelLabel}
          setNumberValue={setLevelValue}
        />
      </div>
      <div className="flex justify-end mt-10 mx-4">
        <ReverseButton
          width="w-1/2"
          label="매치 등록하기"
          isdisabled={isFormValid}
        />
      </div>
    </div>
  );
};

export default MatchRegisterBody;
