#************************************************
#   Ben Michener
#   4/22/2017
#   Intro to Python
#   Assignment 8 (Weather.py)
#************************************************
import Epic
import urllib2
import json

#************************************************
#   Takes a user inputed location and gets the URL
#   from the website for the JSON file about that 
#   location's weather. Crashes when entering a url
#   that returns an error in the JSON file. Error is 
#   in "urllib2.urlopen"? I don't quite understand why.
#************************************************
def getJSON(location):
    if " " in location:
        temp = location.split(" ")
        location = temp[0] + "_" + temp[1]
    url = "https://api.apixu.com/v1/current.json?key=fbcf0dc12c5f43b8947174800172204&q=" + location
    jsonTxt = urllib2.urlopen(url).read()
    weatherJSON = json.loads(jsonTxt)
    
    return weatherJSON
    
#************************************************
#   Prints the weather that is found in the appropriate
#   JSON. Prints in Farenheit for countries that use farenheit,
#   celcisu for most others, and prompts the user for which
#   in canada (as both are accepted froms of measurement in Canada)
#************************************************
def printWeather(JSON):
    country = JSON["location"]["country"]
    if country == "Canada":
        tempType = Epic.userString("Do you want to use Celsius(c) or Fahrenheit(f)?")
    print "Here is the weather for %s, %s (%s)" %(JSON["location"]["name"], JSON["location"]["region"], JSON["location"]["country"])
    if country == "USA" or country == "United States of America" or country == "Bahamas" or country == "Belize" or country == "Palau" or country == "Cayman Islands" or country == "Puerto Rico" or country == "Guam":
        print "\t%s and %s degrees (F)" %(JSON["current"]["condition"]["text"], JSON["current"]["temp_f"])
        print "\tIt actually feels like %s degrees (F) outside." %(JSON["current"]["feelslike_f"])
    elif country == "Canada" and tempType == "c":
        print "\t%s and %s degrees (C)" %(JSON["current"]["condition"]["text"], JSON["current"]["temp_c"])
        print "\tIt actually feels like %s degrees (C) outside." %(JSON["current"]["feelslike_c"])
    elif country == "Canada" and tempType == "f":
        print "\t%s and %s degrees (F)" %(JSON["current"]["condition"]["text"], JSON["current"]["temp_f"])
        print "\tIt actually feels like %s degrees (F) outside." %(JSON["current"]["feelslike_f"])
    else:
        print "\t%s and %s degrees (C)" %(JSON["current"]["condition"]["text"], JSON["current"]["temp_c"])
        print "\tIt actually feels like %s degrees (C) outside." %(JSON["current"]["feelslike_c"])
        
    print""
    
#************************************************
#   Prompts user for a location or zip code and returns
#   the temperature and weather for that area. Loops
#   until the user wishes to quit.
#************************************************
def main():
    print "Let's check the weather!"
    loop = "y"
    
    while loop != "n":
        location = Epic.userString("Where do you want to check? (enter a location or a zip code)")
        
        weatherJSON = getJSON(location)
        printWeather(weatherJSON)
    
        loop = Epic.userString("Do you want to check another location? (y/n)")
        
    print "Enjoy the weather!"
    
    
    
    
main()

#************************************************
#   Sorry, Professor! Nothing too interesting here.
#   I would have put more time into this but I'm getting
#   swamped with work. I promise I'll do something interesting
#   for the last exam!
#************************************************