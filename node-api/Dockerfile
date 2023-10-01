FROM node:17

WORKDIR /node-api
COPY package.json .
RUN npm install
COPY . .
CMD npm start
