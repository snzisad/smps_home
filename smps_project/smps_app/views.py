from django.shortcuts import render
import pyrebase
import firebase_admin
from firebase_admin import credentials, messaging, auth

import pymongo
from django.shortcuts import render
from django.http import HttpResponse, HttpResponseRedirect
import statistics
#from .forms import FilterCriteria

from django.http.response import JsonResponse
from rest_framework.parsers import JSONParser 
from rest_framework import status


from django.shortcuts import render

 
from smps_app.models import Rooms
from smps_app.serializers import RoomsSerializer
from rest_framework.decorators import api_view
 
#from uni_homepage.models import Room
#from uni_homepage.serializers import RoomSerializer
from rest_framework.decorators import api_view
import json


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
#user update variable
user_update=[]
title = "Roomtemperature is high"
body = "High temperature detected. Please reduce the temperature to save energy"
def index(request):
    return render(request,'index.html')
        #accessing our firebase data and storing it in a variable
        # name = database.child('Data').child('Name').get().val()
        # stack = database.child('Data').child('Stack').get().val()
        # framework = database.child('Data').child('Framework').get().val()
        # print(name)
        # context = {
        #     'name':name,
        #     'stack':stack,
        #     'framework':framework
        # }
        # return render(request, 'index.html', context)

def show_user_update(request):
    db_content=Rooms.objects.all()
    return render(request,'show_update.html',{'details':db_content})

def send_topic_push(request):
    firebase_cred = credentials.Certificate("D:\\arctic_hackathon\\smps_project\\smps_app\\smps-home-firebase-adminsdk-2ruep-76103b6db3.json")
    firebase_app = firebase_admin.initialize_app(firebase_cred)

    #send_topic_push(title = "Roomtemperature is high", body = "High temperature detected. Please reduce the temperature to save energy")
    topic = "smps_home"
    message = messaging.Message(
        notification=messaging.Notification(
        title=title,
        body=body
        ),
        topic=topic
    )
    messaging.send(message)
    html = "<html><body>Notification sent</body></html>"
    return HttpResponse(html)
    #return

@api_view(['GET', 'POST', 'DELETE'])
def room_list(request):
    # GET list of tutorials, POST a new tutorial, DELETE all tutorials
    if request.method == 'GET':
      #send_topic_push(title, body)
      with open('D:\\arctic_hackathon\smps_project\smps_app\\fixtures\\rooms.json', 'r') as f:
        my_json_obj = json.load(f)
        print(my_json_obj)
        # rooms = Rooms.objects.all()
        # floor=request.GET.get('floor',None)
        # print(floor)
        # name = request.GET.get('name', None)
        # if name is not None:
        #     rooms = rooms.filter(name__icontains=name)
        # if floor is not None:
        #     rooms=rooms.filter(floor__icontains=int(floor))
        
        # room_serializer = RoomsSerializer(rooms, many=True)
        #return JsonResponse(room_serializer.data, safe=False)
        return JsonResponse(my_json_obj, safe=False)
        # 'safe=False' for objects serialization
    elif request.method == 'POST':
        room_data = JSONParser().parse(request)
        #print(room_data)
        #return JsonResponse(room_data.data, status=status.HTTP_201_CREATED)
        print(room_data["comfortable"])
        room_data["comfortable"] = True if room_data["comfortable"]=="true" else False
        room_data["floor"] = int(room_data["floor"].strip())
        room_data["humidity"] = int(room_data["humidity"].strip())
        room_data["temperature"] = int(room_data["temperature"].strip())
        room_data["CO2"] = int(room_data["CO2"].strip())
        room_data["ventilation"] = int(room_data["ventilation"].strip())
        room_data["facility_outlet"] = int(room_data["facility_outlet"].strip())
        room_data["facility_noise"] = int(room_data["facility_noise"].strip())

        
        print(room_data["comfortable"])
        if(room_data["comfortable"]==False):
          #print("check")
          room_serializer = RoomsSerializer(data=room_data)

          #print("check")
          if room_serializer.is_valid():
              print("check")
              room_serializer.save()
              #save update of user
              #print(type(room_data["comfortable"]))
              #if(room_data["comfortable"]==False):
              user_update.append(room_data)
              print("checking_update",user_update)

              return JsonResponse(room_serializer.data, status=status.HTTP_201_CREATED)
          return JsonResponse(room_serializer.errors, status=status.HTTP_400_BAD_REQUEST)
        else:
          return JsonResponse({"message":"User is comfortable. No change required."}, status=status.HTTP_201_CREATED)
    
    #print("checking_update",user_update)


@api_view(['GET', 'PUT', 'DELETE'])
def room_details(request, pk):
    # find tutorial by pk (id)
    print(pk)
    if request.method == 'GET':
      try: 
          #tutorial = Room.objects.get(pk=pk) 
          with open('D:\\arctic_hackathon\smps_project\smps_app\\fixtures\\rooms.json', 'r') as f:
            my_json_obj = json.load(f)
            print("just element")
            print(my_json_obj[pk])
            # rooms = Rooms.objects.all()
            # floor=request.GET.get('floor',None)
            # print(floor)
            # name = request.GET.get('name', None)
            # if name is not None:
            #     rooms = rooms.filter(name__icontains=name)
            # if floor is not None:
            #     rooms=rooms.filter(floor__icontains=int(floor))
            
            # room_serializer = RoomsSerializer(rooms, many=True)
            #return JsonResponse(room_serializer.data, safe=False)
            return JsonResponse(my_json_obj[pk], safe=False)
      except Exception: 
          return JsonResponse({'message': 'Error occurred in finding room.'}, status=status.HTTP_404_NOT_FOUND) 