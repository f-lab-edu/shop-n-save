 # shop-n-save 🛒 
다양한 상품을 거래할 수 있는 쇼핑몰 프로젝트입니다.
이커머스 기업 쿠팡을 모티브로 제작하였습니다.

* * * 

## 프로젝트 목표
해당 프로젝트는 대규모 트래픽이 발생하는 환경을 전제로 확장 및 유지 보수성이 좋은 코드를 작성하기 위해 노력한 프로젝트입니다.  
본 프로젝트는 View를 사용하지 않고 REST 아키텍처 기반으로 서비스 API를 구현하였기 때문에   
예상하고 의도했던대로 코드가 정확히 동작하는지 파악하기 위한 테스트 코드 작성 또한 중요합니다.  
어떻게하면 더 좋은 테스트를 작성할 수 있을지 고민하며 지속적인 개선을 통해 보완하는 것을 목표로 합니다.  
<br/>
## 사용기술 
Spring Boot, MySQL, Mybatis, Redis  
<br/>  
## 브랜치 관리 전략
Git-Flow 전략을 사용하였습니다.
<img src = "https://user-images.githubusercontent.com/37732016/120079737-48bd9480-c0f0-11eb-8542-99b814fd5e48.JPG" width="400px">  
master : 사용자가 사용하게 될 코드가 있는 브랜치입니다.  
release : 제품의 배포를 준비하는 브랜치입니다. 충분한 테스팅 과정이 이루어집니다.  
develop : 기능 개발 완료 후 코드 리뷰를 마친 코드가 Merge됩니다.  
feature : 기능 개발을 위한 브랜지입니다. feature/이슈번호로 관리됩니다.  
hotfix : 서비스 운영 중 발생한 버그를 처리하는 브랜치입니다.  

##### 참고(우아한 형제들 기술 블로그) : https://woowabros.github.io/experience/2017/10/30/baemin-mobile-git-branch-strategy.html  
<br/>
  
## 프로토타입 👀
![image](https://user-images.githubusercontent.com/37732016/120079284-21fe5e80-c0ee-11eb-8be4-1af37947fc73.png)
프로토타이핑 툴 '카카오 오븐'을 사용하였습니다.
