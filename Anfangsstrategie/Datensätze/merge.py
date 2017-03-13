print("Folgendes Programm fuegt eine Menge an Datensaetzen einer Datenbank hinzu.")
print("Gehe sicher, dass 'anfangsstrategie_datenbank.txt' im gleichen Verzeichnis wie dieses Programm liegt.")
print("Auch muss die Textdatei mit den Datenstaetzen, die hinzugefuegt werden sollen, in diesem Verzeichnis liegen.")
print()

datenbank = open("anfangsstrategie_datenbank.txt", "r")
db = datenbank.readlines()
datenbank.close()
datenbank = open("anfangsstrategie_datenbank.txt", "a")

print("Geben sie den Namen der Datei mit den neuen Datensaetzen an.")
datei_name = raw_input(">>> ")
datei = open(datei_name, "r")
daten = datei.readlines()
datei.close()

i = 0
while i < len(daten) - 1:
    if daten[i] == "Die letzte Eingabe wurde geloescht.":
        del daten[i]
        del daten[i - 1]
        i = i - 2
    i = i + 1

i = 0
while i < len(daten) - 1:
    if db.count(daten[i]) > 0:
        pass
    else:
        datenbank.write(daten[i])
    i = i + 1

datenbank.close()
