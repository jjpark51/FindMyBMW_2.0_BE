# Toy Project - 2023 하반기
[![프로젝트 기획안](/img/project.png)](https://bmwgroupkor.sharepoint.com/:p:/t/Toyproject/EdcI8suchI5FsWRbPx-1EKYBaRkrNO-572FMPj8_sEh7fg?e=6YSZ3J)


# FindMyBMW 

FindMyBMW is a toy project initiated by the 2023-2 IT interns of BMW Group Korea (feat. 2023-2 Marketing Intern Lee Jiyeon & 2023-2 Homologation Intern Park Hanyi) and is aimed at recommending BMW models to potential customers baased on their taste & lifestyle.

# Update!!! 
----------------------------
Version 2.0 
- Backend framework changed from Flask to Spring Boot
- Added MySQL Database
- Added Login/Signup Page
-----------------------------
Slides: https://docs.google.com/presentation/d/1-hYpgo-pytc_sFC-tg3R9Yfoq5CWJVt3/edit?usp=sharing&ouid=116131205847752120819&rtpof=true&sd=true
<br>
<br>

## Table of Contents
* Deployment
* Getting Started
* Example
* Release
* Works
* License

## Deployment

Site URL: https://findmybmw.nsckrsb.aws.bmw.cloud/ (Recommendation results may not appear due to site deployment testing)

* Our frontend code is written using the React framework while the backend utilizes the Flask framework. 
* An automated CI/CD pipeline is implemented using Github Actions
* The server is deployed using nginx on AWS EC2
* AWS S3, Route 53, and CloudFront services were also implemented


## Getting Started

1. Clone Repository
```shell script
$ git clone git@github.com:jjpark51/FindMyBMW_Ext.git
```

2. Install Frontend Dependencies (Make sure Nodejs is installed prior)
```shell script
$ cd FindMyBMW_Ext
$ cd frontend
$ npm install
$ npm start
```

3. Install Backend Dependencies (Powershell / Make sure Python is installed)
```shell script
$ cd FindMyBMW_Ext
$ cd backend
$ python -m venv venv
$ cd venv
$ Scripts/activate
$ pip install -r requirements.txt
$ python server.py
```

## Example
1. Login Page
![image](img/login.png)
2. Main Page
![image](img/main.png)
3. Price Page
![image](img/price.png)
4. Questions Page
![image](img/question.png)
5. Results Page
![image](img/result.png)
6. Detail Page
![image](img/detail.png)
7. Reset Price Page
![image](img/reset.png)

### Release 

| Version  |       Key features                            | OS required                   |
| -------- | ----------------------------------------------| ------------------------------|
|  v.1.0.0 | Fully functional FindMyBMW Prototype          | Chrome browser/Microsoft Edge |
|  v.2.0.0 | Migration from Flask to Spring Boot Framework | Chrome browser/Microsoft Edge |



## [License]
FindMyBMW follow MIT lICESNSE and is freely available for free and may be redistributed under unlimited conditions
