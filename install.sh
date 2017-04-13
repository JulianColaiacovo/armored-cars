sudo apt-get update
curl -sL https://deb.nodesource.com/setup_6.x | sudo -E bash -
sudo apt-get install -y nodejs
sudo apt-get -y install openjdk-8-jdk
sudo apt-get -y install maven

npm config set prefix ${HOME}/npm-global

mvn clean install