#from Django.contrib import admin
#from Django.URLs import path

from django.urls import path
from . import views

urlpatterns = [
    path('', views.index, name='index'),
    #path('show/', views.view_userid, name='view_userid'),
]