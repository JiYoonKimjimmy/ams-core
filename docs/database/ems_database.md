# EMS Database Modeling

***모든 Table 에는 아래와 같은 `Column` 필수로 추가***

| Column | Name | Type | 비고 |
| --- | --- | --- | --- |
| VERSION | 수정 버전 | NUMBER | `ROW` 수정 버전 | 
| CREATED | 생성 일자 | DATE |
| UPDATED | 수정 일자 | DATE |

## 학생 관련 Table 목록
### `STUDENTS` 학생 정보 Table
| Column | Name | Type | 비고 |
| --- | --- | --- | --- |
| ID | 학생 ID | NUMBER | `PK` - Auto increment sequence |
| NAME | 이름 | VARCHAR2 |
| MOBILE_NUMBER | 휴대폰 번호 | VARCHAR2 |
| DATE_OF_BIRTH | 생년월일 | VARCHAR2 | format : `YYYYMMDD` |
| GENDER | 성별 | CHAR |
| SCHOOL | 학교 | VARCHAR2 | 
| GRADE | 학년 | CHAR |

### `STUDENTS_PARENTS` 학생 부모 정보 Table
| Column | Name | Type | 비고 |
| --- | --- | --- | --- |
| ID | 학생 부모 ID | NUMBER | `PK` - Auto increment sequence |
| STUDENT_ID | 학생 ID | NUMBER | `FK` - `STUDENTS`.`ID` |
| NAME | 이름 | VARCHAR2 |
| MOBILE_NUMBER | 휴대폰 번호 | VARCHAR2 |

---

## 시험 관련 Table 목록
### `EXAMS` 시험 정보 Table
| Column | Name | Type | 비고 |
| --- | --- | --- | --- |
| ID | 시험 ID | NUMBER | `PK` - Auto increment sequence |
| NAME | 시험 명 | VARCHAR2 |
| SERIAL_NUMBER | 시험 일련번호 | VARCHAR2 |

### `EXAM_SCHEDULE` 시험 일정 Table
| Column | Name | Type | 비고 |
| --- | --- | --- | --- |
| ID | 시험 일정 ID | NUMBER | `PK` - Auto increment sequence |
| EXAM_ID | 시험 ID | NUMBER | `FK` - `EXAMS`.`ID` |
| TYPE | 시험 일정 구분 | VARCHAR2 | `RECEIPT`: 접수기간<br>`RECEIPT_DONE`: 접수마감<br>`PROCEED`: 시험진행<br>`PROCEED_DONE`: 시험종료 |
| START_DATE | 시작 일자 | DATE |
| END_DATE | 종료 일자 | DATE |

### `EXAM_MANAGEMENT` 시험 관리 기관 정보 Table
| Column | Name | Type | 비고 |
| --- | --- | --- | --- |
| ID | 시험 관리 기관 ID | NUMBER | `PK` - Auto increment sequence |
| EXAM_ID | 시험 ID | NUMBER | `FK` - `EXAMS`.`ID` |
| NAME | 시험 관리 기관 명 | VARCHAR2 |
| PHONE_NUMBER | 시험 관리 기관 전화번호 | VARCHAR2 |
| SITE_URL | 시험 관리 기관 사이트 URL | VARCHAR2 |

### `EXAM_CONTENTS` 시험 기타 정보 Table (시험 접수 방법 등 컨텐츠 관리)
| Column | Name | Type | 비고 |
| --- | --- | --- | --- |
| ID | 시험 기타 정보 ID | NUMBER | `PK` - Auto increment sequence |
| EXAM_ID | 시험 ID | NUMBER | `FK` - `EXAMS`.`ID` |
| CONTENTS | 기타 정보 | VARCHAR2 | 시험 접수 방법 등 `TEXT` 관리 |

---

## Mapping 관련 Table 목록
### `EXAM_PARTICIPATION` 학생 시험 참가 목록 Table
| Column | Name | Type | 비고 |
| --- | --- | --- | --- |
| ID | 학생 시험 참가 ID | NUMBER | `PK` - Auto increment sequence |
| STUDENT_ID | 학생 ID | NUMBER | `FK` - `STUDENTS`.`ID` |
| EXAM_ID | 시험 ID | NUMBER | `FK` - `EXAM`.`ID` |
| STATUS | 상태 | VARCHAR2 | `READY`: 준비<br>`RECEIPT_DONE`: 접수완료<br>`RECEIPT_CANCEL`: 접수취소<br>`PARTICIPATE_DONE`: 참가완료<br>`FINAL_DONE`: 최종완료<br>`NO_RECEIPT`: 미접수<br>`NO_PARTICIPATE`: 미참가 |

### `EXAM_RESULT`학생 시험 결과 목록 Table
| Column | Name | Type | 비고 |
| --- | --- | --- | --- |
| ID | 학생 시험 결과 ID | NUMBER | `PK` - Auto increment sequence |
| STUDENT_ID | 학생 ID | NUMBER | `FK` - `STUDENTS`.`ID` |
| EXAM_ID | 시험 ID | NUMBER | `FK` - `EXAM`.`ID` |
| RESULT | 결과 | VARCHAR2 |
| SCORE | 점수 | VARCHAR2 |

---

## 이력 관련 Table 목록
### `EXAM_STATUS_HISTORY` 학생 시험 상태 변경 이력 Table
| Column | Name | Type | 비고 |
| --- | --- | --- | --- |
| ID | 학생 시험 상태 변경 ID | NUMBER | `PK` - Auto increment sequence |
| STUDENT_ID | 학생 ID | NUMBER | `FK` - `STUDENTS`.`ID` |
| EXAM_ID | 시험 ID | NUMBER | `FK` - `EXAM`.`ID` |
| STATUS | 상태 | VARCHAR2 | `READY`: 준비<br>`RECEIPT_DONE`: 접수완료<br>`RECEIPT_CANCEL`: 접수취소<br>`PARTICIPATE_DONE`: 참가완료<br>`FINAL_DONE`: 최종완료<br>`NO_RECEIPT`: 미접수<br>`NO_PARTICIPATE`: 미참가 |

### `NOTICE_HISTORY` 학생 시험 상태 알림 이력 Table
| Column | Name | Type | 비고 |
| --- | --- | --- | --- |
| ID | 학생 시험 상태 알림 ID | NUMBER | `PK` - Auto increment sequence |
| STUDENT_ID | 학생 ID | NUMBER | `FK` - `STUDENTS`.`ID` |
| EXAM_ID | 시험 ID | NUMBER | `FK` - `EXAM`.`ID` |
| TYPE | 알림 구분 | VARCHAR2 | `RECEIPT`: 접수안내알림<br>`PARTICIPATION`: 시험진행안내알림<br>`RESULT`: 시험결과안내알림 |
| SUCCESS_YN | 알림 성공 여부 | VARCHAR2 |
