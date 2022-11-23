import firebase_admin
from firebase_admin import credentials, messaging, auth

def subscribe_news(tokens): # tokens is a list of registration tokens
    topic = "news"
    response = messaging.subscribe_to_topic(tokens, topic)
    if response.failure_count > 0:
        print(f"Failed to subscribe to topic {topic} due to {list(map(lambda e: e.reason,response.errors))}")


def unsubscribe_news(tokens): # tokens is a list of registration tokens
    topic = "news"
    response = messaging.unsubscribe_from_topic(tokens, topic)
    if response.failure_count > 0:
        print(f"Failed to subscribe to topic {topic} due to {list(map(lambda e: e.reason,response.errors))}")


def send_topic_push(title, body):
    topic = "smps_home"
    message = messaging.Message(
        notification=messaging.Notification(
        title=title,
        body=body
        ),
        topic=topic
    )
    messaging.send(message)


def send_token_push(title, body, tokens):
    message = messaging.MulticastMessage(
        notification=messaging.Notification(
            title=title,
            body=body
        ),
        tokens=tokens
    )
    messaging.send_multicast(message)

if __name__ == "__main__":
    firebase_cred = credentials.Certificate("./smps-home-firebase-adminsdk-2ruep-76103b6db3.json")
    firebase_app = firebase_admin.initialize_app(firebase_cred)

    send_topic_push(title = "Roomtemperature is high", body = "High temperature detected. Please reduce the temperature to save energy")
    
