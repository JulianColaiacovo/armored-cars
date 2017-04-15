sudo apt-get update
curl -sL https://deb.nodesource.com/setup_6.x
sudo apt-get -y install nodejs
sudo apt-get -y install openjdk-8-jdk
sudo apt-get -y install maven

mkdir ${HOME}/npm-global
npm config set prefix ${HOME}/npm-global

mvn clean install