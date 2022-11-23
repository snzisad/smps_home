#from Django.contrib import admin
#from Django.URLs import path

from django.urls import path,re_path
from . import views
#from django.conf.urls import url 

urlpatterns = [
    path('update/',views.show_user_update,name='show_user_update'),
    path('', views.index, name='index'),
    path('send_alert', views.send_topic_push, name='send_topic_push'),
    path('api/rooms/<int:pk>/', views.room_details, name='room_detail'),
    #path('show/', views.view_userid, name='view_userid'),
    # re_path(r'^api/rooms/<int:pk>/', views.room_details,name='room_detail'),
    re_path(r'^api/rooms/', views.room_list,name='room_list'),

]