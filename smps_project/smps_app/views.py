from django.shortcuts import render
import pyrebase

# Create your views here.
firebaseConfig = {
  "apiKey": "AIzaSyBO24huTNrIgs-eMPCyAVKMs5qAj7BkGC8",
  "authDomain": "smps-home.firebaseapp.com",
  "projectId": "smps-home",
  "storageBucket": "smps-home.appspot.com",
  "messagingSenderId": "324485548588",
  "appId": "1:324485548588:web:c3188a088a37c644ec9f89",
  "measurementId": "G-WMNE5RGHPD",
  "databaseURL": "https://smps-home-default-rtdb.europe-west1.firebasedatabase.app/"
}
#here we are doing firebase authentication
firebase=pyrebase.initialize_app(firebaseConfig)
authe = firebase.auth()
database=firebase.database()


def index(request):
        #accessing our firebase data and storing it in a variable
        name = database.child('Data').child('Name').get().val()
        stack = database.child('Data').child('Stack').get().val()
        framework = database.child('Data').child('Framework').get().val()
        print(name)
        context = {
            'name':name,
            'stack':stack,
            'framework':framework
        }
        return render(request, 'index.html', context)
