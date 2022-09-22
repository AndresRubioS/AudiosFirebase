

import pyttsx3
import pyrebase


text_speech = pyttsx3.init()


config = {

"apiKey": "0",

"authDomain": "0",

"databaseURL": "0",

  

"storageBucket": "0"



}
firebase = pyrebase.initialize_app(config)
db = firebase.database()


palabra2 = "yeid"
palabra1 = "hola"
voices = text_speech.getProperty('voices')


voice_id = 'spanish'
    
text_speech.setProperty('voice', voice_id)
salidaLed1 = db.child("message").get()
text_speech.setProperty('rate', 128) 
while True:
    salidaLed1 = db.child("message").get()
   
   
    
    
    if salidaLed1.val() != palabra2:

        palabra2 = salidaLed1.val()

        
        
        print(salidaLed1.val())
        text_speech.say(salidaLed1.val())
        text_speech.runAndWait()

    





