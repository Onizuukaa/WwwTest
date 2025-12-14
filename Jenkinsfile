pipeline {
	agent any

	stages {
		stage('Czyszczenie i Testy') {
			steps {
				// Usunąłem catchError - teraz jak testy padną, Jenkins oficjalnie zgłosi błąd
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
				// WAŻNE: Usunąłem linię 'tokenCredentialId'.
				// Jenkins użyje automatycznie linku (Override URL) z ustawień globalnych.
			)
		}
		failure {
			slackSend (
				channel: '#automatyka',
				color: 'danger',
				message: "🚨 AWARIA: Testy w projekcie ${env.JOB_NAME} nie powiodły się. (<${env.BUILD_URL}|Otwórz>)"
			)
		}
	}
}