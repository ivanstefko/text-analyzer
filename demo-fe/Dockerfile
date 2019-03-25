#base image
FROM node:current-slim

RUN mkdir /usr/src/app
WORKDIR /usr/src/app

COPY ["package.json", "package-lock.json*", "./"]
COPY . .

# add `/usr/src/app/node_modules/.bin` to $PATH
ENV PATH /usr/src/app/node_modules/.bin:$PATH

# install and cache app dependencies
RUN npm install --silent

# start app
CMD ["npm", "start"]