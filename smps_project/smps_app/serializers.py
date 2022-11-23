from rest_framework import serializers 
from smps_app.models import Rooms
 
 
class RoomsSerializer(serializers.ModelSerializer):
 
    class Meta:
        model = Rooms
        fields = ('name',
                  'floor',
                #   'health_humidity',
                #   'health_temp',
                #   'health_sound',
                  'temperature',
                  'humidity',
                  'CO2',
                  'ventilation',
                  'comfortable',
                  'facility_outlet',
                  'facility_noise'
                  )
                  