sudo apt-get update
sudo apt-get -y install openjdk-8-jdk
sudo apt-get -y install maven

touch ~/.npmrc
echo "prefix = ${HOME}/npm-global" > ~/.npmrc

mvn clean install