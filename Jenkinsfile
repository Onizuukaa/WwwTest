pipeline {
	agent any

	stages {
		stage('Czyszczenie i Testy') {
			steps {
				// Uruchamiamy testy. Jeśli padną, pipeline przerwie działanie i przejdzie do sekcji 'failure'
				bat 'gradlew clean test'
			}
		}
	}

	post {
		success {
			slackSend (
				channel: '#automatyka',
				color: 'good',
				message: "✅ SUKCES: Testy w projekcie ${env.JOB_NAME} przeszły pomyślnie! (<${env.BUILD_URL}|Otwórz>)"
				// WAŻNE: Tu NIE MA linii 'tokenCredentialId', bo adres jest już w systemie!
			)
		}
		failure {
			slackSend (
				channel: '#automatyka',
				color: 'danger',
				message: "🚨 AWARIA: Testy w projekcie ${env.JOB_NAME} nie powiodły się. (<${env.BUILD_URL}|Otwórz>)"
				// Tu też usuwamy 'tokenCredentialId'
			)
		}
	}
}