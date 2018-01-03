#************************************************
#   Ben Michener
#   4/7/2017
#   Intro to Python
#   CardSearch.py
#************************************************

import json
import Epic

#************************************************
#   Reads in the json file and returns it to main
#************************************************

def readFile(fileName):
    jsonTxt = ""
    f = open(fileName)
    for line in f:
        line = line.strip()
        jsonTxt = jsonTxt + line
    cardList = json.loads(jsonTxt)
    return cardList
    
#************************************************
#   Allows the user to view all items in the lists
#   under each key in each deck. 
#************************************************
    
def searchFile(cardList, deck):
    for decks in cardList:
        if decks["Name"] == deck:
            category = Epic.userString("What do you want to look at? (\"Monster\", \"Spell\", \"Trap\", \"Extra\")")
            if category != "Monster" and category != "Spell" and category != "Trap"  and category != "Extra":
                print "Please enter a valid category"
            else:
                print "\n**********************************************************************************************************************************************"
                print "Here are all the %s cards in your %s deck" % (category.lower(), deck)
                print "================================================================"
                for card in decks[category]:
                    print "- %s" % card
                print "**********************************************************************************************************************************************\n"
            
#************************************************
#   Alows the user to view all of the current decks
#   on file and browse each deck by card type. The 
#   user can continue doing this until they wish to
#   quit. The number of decks can be expanded indefinitely,
#   as long as they follow the same format as the decks
#   in the current json file.
#************************************************

def main():
    cardList = readFile('cards.json')
    
    print "**********************************************************************************************************************************************"
    print "Welcome to CardSearch. We keep track of your current decks and provide an easy way to search through your decks."
    print "Here is a list of your current decks:"
    for deck in cardList:
        print deck["Name"]
    print "**********************************************************************************************************************************************\n"
    loop = True
    
    while loop == True:
        deck = Epic.userString("Which Deck do you wish to look at? (q to quit)")
        if deck == "q":
            loop = False
        else:
            searchFile(cardList, deck)
    
    print "Thank you for using CardSearch. Happy Dueling."
        

main()

#************************************************
#   Note: I only put in my two current decks and 
#   my girlfriend's deck to start, but I actually
#   intend to continue updating the lists as time
#   goes on. I like the format that this is in
#************************************************