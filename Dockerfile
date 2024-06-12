# 베이스 이미지로 Node.js를 사용합니다.
FROM node:14

# 작업 디렉토리를 생성하고 설정합니다.
WORKDIR /usr/src/app

# package.json과 package-lock.json 파일을 복사합니다.
COPY package*.json ./

# 프로젝트의 의존성을 설치합니다.
RUN npm install

# 애플리케이션 코드를 복사합니다.
COPY . .

# 애플리케이션이 4000번 포트를 사용한다고 가정합니다.
EXPOSE 4000

# 애플리케이션을 시작합니다.
CMD ["npm", "start"]