- `setGame` - 게임 시작 전 세팅
  - `printOutputString` - 게임 시작에 대한 문자열 출력
  - `saveRandomNumber` - 컴퓨터가 랜덤한 숫자 3개를 저장함.
    - `getRandomNumber` - 랜덤한 숫자 하나를 반환함.
    - `isDuplicate` - `getRandomNumber`에서 반환한 숫자가 중복인지 확인함.
  

- `inputPlayerValue` - 사용자의 입력을 받고 저장함.
  - `isRightInputLength` - 사용자가 입력한 값이 3글자가 맞는지 확인, 옳지 않으면 `notRightInputForm` 실행함.
  - `savePlayerNumber` - 사용자가 입력한 값을 저장함.

- `compareValue` - 사용자가 입력한 값과 컴퓨터가 랜덤으로 지정한 값을 비교하고 나온 결과 반환함.
  - `getStrike` - 두 값을 비교하여 스트라이크의 수를 반환함.
    - `isThreeStrike` - 3스트라이크가 나왔으면 true 반환함.
  - `getBall` - 두 값을 비교하여 볼의 수를 반환함.
    - `isEqualsValue` - 두 숫자가 같으면 true, 다르면 false 반환함.
  - `isNotThing` - 스트라이크의 수와 볼의 수 둘 다 0일 경우 true 반환함.


- `setGameOver` - 게임 중단 세팅
  - `isRightGameSetCommand` - 입력된 값이 1 또는 2가 맞다면 true 반환함, 아니라면 `notRightInputForm` 실행함.


- `notRightInputForm` - `IllegalArgumentException`을 발생시켜 애플리케이션을 종료하도록 함.


- `getOutputString` - 입력 값에 따라 출력될 문자열을 선정함.