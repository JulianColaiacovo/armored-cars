sudo apt-get update
sudo apt-get -y install nodejs-legacy
sudo apt-get -y install npm
sudo apt-get -y install openjdk-8-jdk
sudo apt-get -y install maven

mkdir ${HOME}/npm-global
npm config set prefix ${HOME}/npm-global
export PATH="${HOME}/npm-global/bin:$PATH"

mvn clean install