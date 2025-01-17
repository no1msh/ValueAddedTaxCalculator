# 부가가치세(VAT) 계산기

## 프로젝트 추가 방법

1. JitPack repository를 추가합니다.
```kotlin
dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    google()
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
  }
}
```

2. 의존성을 추가합니다.
```kotlin
dependencies { 
    implementation("com.github.no1msh:ValueAddedTaxCalculator:1.0.2")
}
```

## 기능 목록
- **합계금액을 기준**으로 부가세를 계산할 수 있다.
  - ex) **부가세를 포함하여** 5000원일 때의 부가세가 얼마인지 계산
- **공급가액을 기준**으로 부가세를 계산할 수 있다.
  - ex) **부가세를 포함하지 않고** 공급가액이 5000원일 때 부가세가 얼마인지 계산
- 10%를 계산했을 때 결과값은 **소수점 단위에서 반올림한다.**
  - ex) 45.5원 -> 46원
- **매입가가 있다면 그만큼 부가세가 감소한다.**
  - ex) 10,000원 매출, 원가 2,000원이면 부가가치 8,000원에서 10% => 부가세액 800원
- **일반과세자의 기준**으로 계산한다.

### 용어 정리
- **부가가치**
  - 제품의 원가에서 판매금액까지의 가치
  - ex) 원가 2000원의 물건을 5000원에 판매 -> 3000원 부가가치
- **부가가치세(부가세, VAT)**
  - 부가가치에 과세하는 세금
- **공급가액**
  - 부가세가 포함되지 않은 물건 및 서비스의 가격
- **매입세액**
  - 매입값 x 10%(0.1) = 매입세액
- **매출세액**
  - 매출액 x 10%(0.1) = 매출세액
- **일반과세자**
  - 연간 매출액이 10,400만원 이상인 사업자
  - 세금계산방법:  매출세액 - 매입세액
