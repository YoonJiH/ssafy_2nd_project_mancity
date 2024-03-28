interface TailwindPropsType {
  type?: string;
  style?: any;
  textSize?: string;
  fontWeight?: string;
  textColor?: string;
  bgColor?: string;
  borderColor?: string;

  hoverTextColor?: string;
  hoverBgColor?: string;
  hoverBorderColor?: string;

  width?: string;
  height?: string;
  rounded?: string;
  label?: string;
  placeholder?: string;
  textValue?: string;
  setTextValue?: React.Dispatch<React.SetStateAction<string>>;
  isdisabled?: boolean;

  bgimg?: string;
  file?: string;
  hover?: boolean;

  reverse?: boolean;
}

interface WideCardPropsType {
  file?: string;
  subtext: string;
  maintext: string;
  minitext?: string;
  buttonlabel: string;
}

interface CommunityCardPropsType {
  file?: string;
  subtext: string;
  maintext: string;
  likes: number;
  comments: number;
  toWhere: number;
}

interface ClubListPropsType {
  bgimg?: string;
  clubTitile: string;
  clubInfo: string;
  file?: string;
}

interface ShadcnDropdownPropsType {
  items: { value: number; label: string }[];
  width?: string;
  position: string | undefined;
  setPosition?: React.Dispatch<React.SetStateAction<string>>;
  setNumberValue?: React.Dispatch<React.SetStateAction<number>>;
}

interface DropdownPropsType extends ShadcnDropdownPropsType {
  typographyLabel: string;
}

interface HeaderPropsType {
  label?: string;
  backArrow: boolean;
  headerButton: boolean;
  onClickButton?: () => void;
  buttonLabel?: string;
  toWhere?: string;
}

interface InputGroupPropsType {
  type?: string;
  typographyLabel: string;
  placeholder?: string;
  checking: boolean;
  checkingLabel?: string;
  textValue?: string;
  setTextValue?: React.Dispatch<React.SetStateAction<string>>;
  setIsCheck?: React.Dispatch<React.SetStateAction<boolean | null>>;
}

interface GlobalCardProps {
  mainTitle: string;
  subTitle: string;
  file?: string;
}

interface HighlightCardProps {
  mainTitle: string;
  file?: string;
}

interface ExampleCustomInputProps extends React.HTMLProps<HTMLButtonElement> {
  value?: string;
  onClick?: React.MouseEventHandler<HTMLButtonElement>;
}

interface MiniMapPropsType {
  lat: number;
  lng: number;
  address: string;
  tel: string;
  onClick: () => Promise<void>;
}

interface MapPropsType {
  lat: number;
  lng: number;
}

interface MemberListPropsType {
  label: string;
}

interface ImgBoxType {
  file: string;
  width: string;
  height: string;
  rounded: string;
}

interface GlobalSwitchPropsType {
  label: string;
  isSwitchOn: boolean;
  setIsSwitchOn: React.Dispatch<React.SetStateAction<boolean>>;
  switchMarginTop: string;
}

interface TypographyLinePropsType {
  lineWidth: string;
  label: string;
}

interface FooterPropsType {
  label: string;
  buttonLabel: string;
  onButtonClick: () => void;
}

interface TextareaContainerPropsType {
  textareaValue: string;
  setTextareaValue: React.Dispatch<React.SetStateAction<string>>;
}

interface FollowCardPropsType {
  file: string;
  nickName: string;
  overall: number;
}

interface matchPlace {
  value: number;
  label: string;
}

interface muiModalPropsType {
  open: boolean;
  setOpen: React.Dispatch<React.SetStateAction<boolean>>;
  handleOpen: () => void;
  handleClose: () => void;
}

interface userStatModalPropsType {
  userId: number;
}
