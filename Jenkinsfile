node {
  deleteDir()
  checkout scm
  stage "install dependencies"
  sh "sbt update"

  stage "compile"
  sh "sbt compile"

  sh "docker images"

  sh "whoami"

  sh "docker run --name buildcontainer -it dr0l3/sbtbuildcontainer /bin/bash"

  sh "git clone https://github.com/dr0l3/spraySbtTutorial.git"

  sh "cd spraySbtTutorial"

  sh "sbt test"

  sh "exit"

  sh "docker rm -f buildcontainer"


  stage "package"
  sh "sbt assembly"


  def deployImage = docker.build "dr0l3/testsbt:${env.BUILD_NUMBER}"

  docker.withRegistry("https://docker.io", "docker-registry-login") {
    deployImage.push "latest"
  }

  stage "deploy"
  sh './deploy.sh'

  stage "smoke test"
  sh 'sleep 5'
  sh 'curl localhost:8081/colormesilly'
  deleteDir()
}