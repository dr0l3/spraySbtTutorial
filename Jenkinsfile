node {
  deleteDir()
  checkout scm
  sh "sbt update"

  stage "install dependencies"
  sh "sbt update"

  stage "compile"
  sh "sbt compile"

  def testImage = docker.image('dr0l3/sbtbuildcontainer')
  testImage.pull()
  testImage.inside("-v /var/lib/jenkins/.ivy2/:/root/.ivy2/:rw -v /var/lib/jenkins/.sbt/:/root/sbt:rw" -v ./:/root/:rw) {
    stage "test"
       sh "sbt compile test"
  }

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