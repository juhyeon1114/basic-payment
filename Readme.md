## 🔥 백그라운드

서버를 실행하면, ```resources/data.sql```이 실행되고, 아래의 데이터가 기본적으로 생성됨.

1. ```id```가 ```12345```인 유저
2. 위 유저의 잔액 데이터: ```1230.123 KRW```,  ```123.123 USD```
3. ```id```가 ```merchantId123```인 상점

## ⚡ 실행하는 법

- 인텔리제이로 실행하거나 아래 명령어를 통해서 실행

```shell
chomod +x ./gradlew # gradlew 실행 권한이 없을 경우 실행
```

```shell
./gradlew bootRun
```


## 📒 Url 정보

- 서버 애플리케이션: http://localhost:8080
- Swagger 문서: http://localhost:8080/swagger/index.html
- H2 콘솔: http://localhost:8080/h2