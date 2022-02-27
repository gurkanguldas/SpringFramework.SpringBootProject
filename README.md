# InnovaBitirmeProjesi

Projenin amacı kullanıcıdan gerekli bilgilerinin alınarak krediye uygunluğu ve kredi limitini hesaplamaktır. Bu kapsamda mikroservis mimarisi yaklaşımınada uygun olması açısından projenin backendi PersonService ve CreditServis şeklinde tasarlanmıştır. PersonService servisinde kişiye ait işlemler, CreditService servisinde ise kredilere ait işlemler yapılmaktadır. PersonService ve CreditService servislerinin haberleşmesi FrontendService servisi tarafından sağlanmaktadır.

Projede CreditService için Spring Boot + MongoDB, PersonService için Spring Boot + MySQL ve FrontendService için Spring Boot + Thymeleaf + Bootstrap teknolojileri kullanılmıştır.

## Projede Kullanılan Teknolojiler


* [Maven](https://maven.apache.org/guides/index.html)
* [Thymeleaf](https://docs.spring.io/spring-boot/docs/2.6.3/reference/htmlsingle/#boot-features-spring-mvc-template-engines)
* [Validation](https://docs.spring.io/spring-boot/docs/2.6.3/reference/htmlsingle/#boot-features-validation)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.6.3/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.6.3/reference/htmlsingle/#production-ready)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/2.6.3/reference/htmlsingle/#configuration-metadata-annotation-processor)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.6.3/reference/htmlsingle/#using-boot-devtools)
* [Spring Data JPA](https://docs.spring.io/spring-boot/docs/2.6.3/reference/htmlsingle/#boot-features-jpa-and-spring-data)
* [Spring Data MongoDB](https://docs.spring.io/spring-boot/docs/2.6.3/reference/htmlsingle/#boot-features-mongodb)
* [MySQL Connector]()
## Projede Kullanılan Araçlar
* [IntelliJ IDEA + JAVA 11]()
* [Git]()
* [Docker]()
## Projenin Mimarisi Ve Derlenmesi
![](https://user-images.githubusercontent.com/59657939/155844558-9d90401d-3dba-42c7-b502-46f0a61425a7.png)


## Derleme Aşamaları
***
***
### 1. Jar File Üretimi
***
Java ve Maven sürüm bilgisi için;
```
java --version
mvn --version
```
 Jar filenin üretimi
```
./mvnw clean package -DskipTests
```

### 2. Container Üretimi
***
Docker sürüm bilgisi için;
```
docker --version
```
FrontendService Docker ve docker-compose dosyaları

[Dockerfile](https://github.com/gurkanguldas/InnovaBitirmeProjesi/blob/main/FrontendService/Dockerfile)

[docker-compose](https://github.com/gurkanguldas/InnovaBitirmeProjesi/blob/main/FrontendService/docker-compose.yml)

CreditService Docker ve docker-compose dosyaları

[Dockerfile](https://github.com/gurkanguldas/InnovaBitirmeProjesi/blob/main/CreditService/Dockerfile)

[docker-compose](https://github.com/gurkanguldas/InnovaBitirmeProjesi/blob/main/CreditService/docker-compose.yml)

PersonService Docker ve docker-compose dosyaları

[Dockerfile](https://github.com/gurkanguldas/InnovaBitirmeProjesi/blob/main/PersonSevice/Dockerfile)

[docker-compose](https://github.com/gurkanguldas/InnovaBitirmeProjesi/blob/main/PersonSevice/docker-compose.yml)

Docker image ve docker container üretimi ve servislerinin ayağa kaldırılması için;

**NOT:** FrontendService containeri bir bridge network oluşturmakta ve diğer containerler oluşan networke bağlanmaktadır. Bu sebeble öncelikle **[FrontendService](https://github.com/gurkanguldas/InnovaBitirmeProjesi/tree/main/FrontendService)'in** ayağa kaldırılması gerekir.
```
docker-compose up
```
### 3. Servis Linkleri
***
FrontendService
* Anasayfa (kişi kayıt formu)
    * [http://localhost:8080/person/create](http://localhost:8080/person/create)
* Kayıt
    * [http://localhost:8080/person/save](http://localhost:8080/person/save)
* Güncelleme
    * [http://localhost:8080/person/update?personIdentificationNumber=12345612345](http://localhost:8080/person/update?personIdentificationNumber=1324561234)
* Silme
    * [http://localhost:8080/person/delete](http://localhost:8080/person/delete)
* Kredi Durumu
    * [http://localhost:8080/credit/status](http://localhost:8080/credit/status)

CreditService
* Kayıt ve Güncelleme
    * [http://localhost:8082/rest/credit/save](http://localhost:8082/rest/credit/save)
* Silme
    * [http://localhost:8082/rest/credit/delete?id=id](http://localhost:8082/rest/credit/delete?id=id)
* Id'ye Göre Kişi
    * [http://localhost:8082/rest/credit/get?id=a1b2c3](http://localhost:8082/rest/credit/get?id=a1b2c3)
* Kimlik Numarasına Göre Kişi
    * [http://localhost:8082/rest/credit/get/identity?id=12345612345](http://localhost:8082/rest/credit/get/identity?id=12345612345)
* Kişi Listesi
    * [http://localhost:8082/rest/credit/get/credits](http://localhost:8082/rest/credit/get/credits)

    
PersonService
* Kayıt ve Güncelleme
    * [http://localhost:8081/rest/person/save](http://localhost:8081/rest/person/save)
* Silme
    * [http://localhost:8081/rest/person/delete?id=1](http://localhost:8081/rest/person/delete?id=1)
* Id'ye Göre Kişi
    * [http://localhost:8081/rest/person/get?id=1](http://localhost:8081/rest/person/get?id=1)
* Kimlik Numarasına Göre Kişi
    * [http://localhost:8081/rest/person/get/identity?id=1234561234](http://localhost:8081/rest/person/get/identity?id=1234561234)
* Kişi Listesi
    * [http://localhost:8081/rest/person/get/people](http://localhost:8081/rest/person/get/people)
### 4. Frontend
***
![1](https://user-images.githubusercontent.com/59657939/155845974-1a66d075-bab0-4c68-bba4-b57e7d1f713c.png)
![2](https://user-images.githubusercontent.com/59657939/155845977-f1c2a7ee-1973-4edc-81ed-bf70c127ca24.png)
![3](https://user-images.githubusercontent.com/59657939/155845979-cb993fee-c08f-4653-b488-329bbef602b8.png)
### 5. Docker Container, Image ve Network İşlemleri
***
Docker'da ki Container, Image ve Networkleri Listeleme
```
docker container ps -a
docker images
docker network ls
```
Docker'da çalışan tüm containerleri durdurma
```
docker stop $(docker ps -a -q)
```
Docker'da ki Container, Image ve Networkleri Temizleme
```
docker container prune
docker image prune -a
docker network prune
```
***
Sayın [Hamit Mızrak](https://github.com/hamitmizrak) ve [Ramazan Sakin](https://github.com/ramazansakin) hocama gelişimimdeki katkılarından dolayı teşekkür ederim. Ayrıca [patika.dev](https://www.patika.dev/tr) ekibinin böyle bir organizasyona ev sahipliği yaptığı için teşekkür etmek isterim.
