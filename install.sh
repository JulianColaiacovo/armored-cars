sudo apt-get update
sudo apt-get -y install nodejs
sudo apt-get -y install npm
sudo apt-get -y install openjdk-8-jdk
sudo apt-get -y install maven

mkdir ${HOME}/npm-global
npm config set prefix ${HOME}/npm-global

mvn clean install