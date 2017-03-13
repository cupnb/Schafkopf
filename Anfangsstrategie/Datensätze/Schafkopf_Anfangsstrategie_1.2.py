import time
import random
import pickle

# hi hi

def eingabeInInt():
    try:
        i = input(">>> ")
        i = int(i)
        return i
    except ValueError:
        print("Ungültige Eingabe.")
        return eingabeInInt()
    


# Erzeugt eine Text Datei mit Name des Benutzers und Zeitstempel
print("Gib deinen Namen ein. Setze bitte keine Leerzeichen.")
name = str(input(">>> "))
zeit = str(time.strftime("%c"))
datei = open(name+"_"+"Anfangsstrategie_1.0" + ".txt", "w")
dateiZahlen = open(name+"_"+"Anfangsstrategie_Zahlen_1.0" + ".txt", "w")

# Deck erstellen

Deck = []
Symbole = ["S","G","E","H"]

for j in range(0,4):
    for i in range(7,10):
        Deck.append(Symbole[j]+str(i))
    Deck.append(Symbole[j]+"K")
    Deck.append(Symbole[j]+"10")
    Deck.append(Symbole[j]+"A")

Symbole = ["S","H","G","E"]

for j in range(0,4):
    Deck.append(Symbole[j]+"U")

for j in range(0,4):
    Deck.append(Symbole[j]+"O")

Zahlen = []
for i in range(0,32):
    Zahlen.append(i)

Wahl = [-1]
for i in range(0,8):
    Wahl.append(i)

# -1: nicht spielen
# Sauspiel:
#   0: Schellen
#   1: Gras
#   2: Eichel
# Farbsolo:
#   3: Schellen
#   4: Gras
#   5: Herz
#   6: Eichel
# 7: Wenz


Input = []

while 1:

    Blatt = []
    Zahlenblatt = []
    for i in range(0,8):
        c = random.choice(Zahlen)
        while Zahlenblatt.count(c) == 1:
            c = random.choice(Zahlen)
        Zahlenblatt.append(c)
        
    Zahlenblatt.sort()
    for i in range(0,8):
        Blatt.append(Deck[Zahlenblatt[i]])
    
    print(Blatt)
    wahl = str(input(">>> "))

    if wahl == "f":
        break

    elif wahl == "del":
        print("Die letzte Eingabe wurde gelöscht.")
        del Input[len(Input) - 1]
        datei.write("Die letzte Eingabe ist ungueltig. \n")
        dateiZahlen.write("Die letzte Eingabe ist ungueltig. \n")

    else:
        
        try:
            wahl = int(wahl)
        except ValueError:
            print("Ungültige Eingabe.")
            wahl = eingabeInInt()
    
        while Wahl.count(wahl) != 1:
            print("Ungültige Eingabe.")
            wahl = input(">>> ")

            try:
                wahl = int(wahl)
            except ValueError:
                print("Ungültige Eingabe.")
                wahl = eingabeInInt()

    
    Blatt.append(wahl)
    Zahlenblatt.append(wahl)
    Input.append(Zahlenblatt)

    datei.write(str(Blatt)+"\n")
    dateiZahlen.write(str(Zahlenblatt)+"\n")
    
datei.close()
dateiZahlen.close()
p = open(name+"_"+"Anfangsstrategie_1.0"+".pkl","bw")
pickle.dump(Input,p)
p.close()
