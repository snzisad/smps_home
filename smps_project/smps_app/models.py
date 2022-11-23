from django.db import models

# Create your models here.

from django.db import models as model1
from djongo import models as models2
from django.contrib.postgres.fields import ArrayField,JSONField
from django.core.validators import validate_comma_separated_integer_list
'''
# Create user form.
from django.db import models
from django.contrib.auth.models import (
    BaseUserManager, AbstractBaseUser
)


class MyUserManager(BaseUserManager):
    def create_user(self, email, date_of_birth, password=None):
        """
        Creates and saves a User with the given email, date of
        birth and password.
        """
        if not email:
            raise ValueError('Users must have an email address')

        user = self.model(
            email=self.normalize_email(email),
            date_of_birth=date_of_birth,
        )

        user.set_password(password)
        user.save(using=self._db)
        return user

    def create_superuser(self, email, date_of_birth, password=None):
        """
        Creates and saves a superuser with the given email, date of
        birth and password.
        """
        user = self.create_user(
            email,
            password=password,
            date_of_birth=date_of_birth,
        )
        user.is_admin = True
        user.save(using=self._db)
        return user


class MyUser(AbstractBaseUser):
    email = models.EmailField(
        verbose_name='email address',
        max_length=255,
        unique=True,
    )
    date_of_birth = models.DateField()
    is_active = models.BooleanField(default=True)
    is_admin = models.BooleanField(default=False)

    objects = MyUserManager()

    USERNAME_FIELD = 'email'
    REQUIRED_FIELDS = ['date_of_birth']

    def __str__(self):
        return self.email

    def has_perm(self, perm, obj=None):
        "Does the user have a specific permission?"
        # Simplest possible answer: Yes, always
        return True

    def has_module_perms(self, app_label):
        "Does the user have permissions to view the app `app_label`?"
        # Simplest possible answer: Yes, always
        return True

    @property
    def is_staff(self):
        "Is the user a member of staff?"
        # Simplest possible answer: All admins are staff
        return self.is_admin


#class aip_rooms(models.Model):
#    name = models.CharField(max_length=70, blank=False, default='')
#    floor = models.IntegerField(blank=False, default=0)
    #published = models.BooleanField(default=False)
    #def save(self, *args, using=None, **kwargs):
     #   super(Room, self).save(*args, using='aip_building', **kwargs)

class Hero(models.Model):
    name = models2.CharField(max_length=60)
    alias = models2.CharField(max_length=60)
    def __str__(self):
        return self.name
'''

class Rooms(models2.Model):
    #_id = models2.ObjectIdField()
    #_id=models2.IntegerField()
    name = models2.CharField(max_length=70, blank=False, default='')
    floor = models2.IntegerField(blank=False, default=0)
    #health_temp=ArrayField(model1.IntegerField(blank=True),default=list)
    #cart_products = ArrayField(
    #    models.IntegerField(blank=True),
    #    default = list
    #)
    temperature=models2.IntegerField()
    #health_humidity=ArrayField(model1.IntegerField(blank=True),default=list)
    #health_sound=ArrayField(model1.IntegerField(blank=True),default=list)
    humidity=models2.IntegerField()
    CO2=models2.IntegerField()
    ventilation=models2.IntegerField()
    comfortable=models2.BooleanField(max_length=70, blank=False, default='')
    facility_outlet=models2.IntegerField()
    facility_noise=models2.IntegerField()
    class Meta():
            #app_label="University"
            db_table = "Rooms"
            verbose_name_plural = 'Rooms'
    def __str__(self):
            return self.name


