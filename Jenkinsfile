node {
  deleteDir()
  checkout scm
  stage "install dependencies"
  sh "sbt update"

  stage "compile"
  sh "sbt compile"

  stage "test"
  sh "sbt test"

  stage "assembly"
  sh "sbt assembly"

  stage "build image"
  def deployImage = docker.build "dr0l3/testsbt:${env.BUILD_NUMBER}"

  docker.withRegistry("https://docker.io", "docker-registry-login") {
    deployImage.push "latest"
  }

  stage "deploy"
  sh './deploy.sh'

  stage "smoke test"
  sh 'sleep 5'
  sh 'curl localhost:8081/colormesilly'

  stage "clean up"
  deleteDir()
}