- `playGame` - 하나의 게임 담당
  - `saveRandomNumber` - 컴퓨터가 랜덤한 숫자 3개를 저장함.
    - `getRandomNumber` - 랜덤한 숫자 하나를 반환함.
    - `isDuplicate` - `getRandomNumber`에서 반환한 숫자가 중복인지 확인함.
  - `inputAndCompareValue` - 사용자의 값에 대한 처리 및 컴퓨터 선정 값과 비교 담당
    - `inputPlayerValue` - 사용자의 입력을 받고 저장함.
    - `savePlayerNumber` - 사용자가 입력한 값을 저장하며, 중복이 있다면 notRightInputForm 실행함.
      - `isRightInputForm` - 사용자가 입력한 값이 3글자가 맞는지, 1에서 9의 값으로만 이루어져 있는지 확인, 옳지 않으면 `notRightInputForm` 실행함.
    - `isThreeStrike` - 랜덤 값과 사용자 값을 비교하여 3스트라이크가 나올 시 true 반환함.
      - `getStrike` - 두 값을 비교하여 스트라이크의 수를 반환함.
      - `getContainCount` - 두 값을 비교하여 서로 포함되어 있는 숫자의 수를 반환함.
      - `printResult` - 결과에 대한 문구를 출력함.
  - `setGameOver` - 게임 중단 세팅, 게임을 중단할 시 false 반환함.
    - `isRightReplayCommandForm` - 입력된 값이 1 또는 2가 맞다면 true 반환함.


- `notRightInputForm` - `IllegalArgumentException`을 발생시켜 애플리케이션을 종료하도록 함.