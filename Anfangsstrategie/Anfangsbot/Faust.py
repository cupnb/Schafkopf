

# Das Deck

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

# nuetzliche Methoden:

def anzahlTrumpf(Blatt):
    a = 0
    for i in range(0, len(Blatt)):
        if Blatt[i] >= 18:
            a = a + 1
    return a

def anzahlSau(Blatt):
    a = 0
    for i in range(0, len(Blatt)):
        if [5, 11, 17].count(Blatt[i]) == 1:
            a = a + 1
    return a

def anzahlOber(Blatt):
    a = 0
    for i in range(0, len(Blatt)):
        if [31, 30, 29, 28].count(Blatt[i]) == 1:
            a = a + 1
    return a

def anzahlUnter(Blatt):
    a = 0
    for i in range(0, len(Blatt)):
        if [27, 26, 25, 24].count(Blatt[i]) == 1:
            a = a + 1
    return a

def anzahlBrems(Blatt):
    a = 0
    for i in range(0, len(Blatt)):
        if [31, 30, 29].count(Blatt[i]) == 1:
            a = a + 1
    return a

def istFrei(Blatt):
    for i in range(0,3):
        frei = True
        for j in range(0,6):
            if Blatt.count(i*6+j):
                frei = False
        if frei == True:
            return True
    return False

def unterschiedlicheSpatzen(Blatt):
    for k in range(0,3):
        test = False
        for j in range(0,8):
            if [5*k, 5*k+1, 5*k+2].count(Blatt[j]) > 0:
                test = True
        if test == False:
            return False
    return True

def anzahlSchelle(Blatt):
    a = 0
    i = 0
    while i < len(Blatt):
        if Blatt[i] >= 0 and Blatt[i] <= 5:
            a = a + 1
        i = i + 1
    return a

def anzahlGras(Blatt):
    a = 0
    i = 0
    while i < len(Blatt):
        if Blatt[i] >= 6 and Blatt[i] <= 11:
            a = a + 1
        i = i + 1
    return a

def anzahlEichel(Blatt):
    a = 0
    i = 0
    while i < len(Blatt):
        if Blatt[i] >= 12 and Blatt[i] <= 17:
            a = a + 1
        i = i + 1
    return a

def waehleSau(Blatt):
    # Nimm die Trumpf und Sau raus
    i = 0
    while i < len(Blatt):
        if Blatt[i] >= 18 or [5,11,17].count(Blatt[i]) == 1:
            del Blatt[i]
            i = i - 1
        i = i + 1

    # Sortiere in Farben
    s = []
    g = []
    e = []
    z = []

    i = 0
    while i < len(Blatt):
        if Blatt[i] >= 0 and Blatt[i] <= 5:
            s.append(Blatt[i])
        elif Blatt[i] >= 6 and Blatt[i] <= 11:
            g.append(Blatt[i])
        elif Blatt[i] >= 12 and Blatt[i] <= 17:
            e.append(Blatt[i])
        i = i + 1

    if len(s) <= len(g) and len(s) <= len(e) and len(s) != 0:
        z.append(s)
    if len(g) <= len(e) and len(g) <= len(s) and len(g) != 0:
        z.append(g)
    if len(e) <= len(s) and len(e) <= len(g) and len(e) != 0:
        z.append(e)

    if len(z) == 1:
        if z[0] == s:
            return 0
        elif z[0] == g:
            return 1
        elif z[0] == e:
            return 2

    if z.count(s) == 1:
        s_best = ( s[len(s)-1] + 1 ) % 6
    else:
        s_best = -1

    if z.count(g) == 1:
        g_best = ( g[len(g)-1] + 1 ) % 6
    else:
        g_best = -1

    if z.count(e) == 1:
        e_best = ( e[len(e)-1] + 1 ) % 6
    else:
        e_best = -1

    z = []
    if g_best >= s_best and g_best >= e_best:
        z.append(g_best)

    if e_best >= g_best and e_best >= s_best:
        z.append(e_best)

    if s_best >= g_best and s_best >= e_best:
        z.append(s_best)

    if z[0] == s_best:
        return 0
    elif z[0] == g_best:
        return 1
    elif z[0] == e_best:
        return 2



def Anfang(Blatt, pos):

    spielbar = []
    riskant = []
    t = anzahlTrumpf(Blatt)
    s = anzahlSau(Blatt)
    o = anzahlOber(Blatt)
    u = anzahlUnter(Blatt)
    b = anzahlBrems(Blatt)


    # Sauspiel?

    if ( t == 6 and s < 2 ) or ( t == 7 and s < 1 ):
        return waehleSau(Blatt)

    elif t == 5 and s < 3 and ( s > 0 or istFrei(Blatt) == True ):
        return waehleSau(Blatt)

    elif t == 5 and unterschiedlicheSpatzen(Blatt) == True and ( ( o >= 2 and u >= 1 ) or ( o >= 3 ) ) and s == 0:
        return waehleSau(Blatt)

    elif t == 4 and ( ( b >= 1 and u >= 1 ) or ( b >= 1 and o >= 2) ) and s == 2:
        return waehleSau(Blatt)



    # Wenz?

    stiche = 0


    # Wie steht es mit den Untern?

    if u == 4:                              # 4 Unter:
        pass                                # Alles cool

    elif u == 3 and Blatt.count(27) == 1:   # 3 Unter mit Eichelunter?
        pass                                # immer noch alles cool, den vierten holst du dir noch.

    elif u == 3 and Blatt.count(27) == 0:   # 3 Unter ohne Eichelunter?
        stiche = stiche + 1                 # kritisch, ein Trumpfstich :c

    elif u == 2 and Blatt.count(27) == 1 and Blatt.count(26) == 1:  # Eichel- und Grasunter?
        pass                                                        # alles cool

    elif u == 2 and Blatt.count(27) == 1 and Blatt.count(26) == 0:  # Eichel- aber kein Grasunter?
        stiche = stiche + 1                                         # kritisch

    elif u == 1 and Blatt.count(27) == 1:   # Nur den Eichelunter?
        stiche = stiche + 1                 # mal schauen...

    elif u == 1 and Blatt.count(27) == 0:   # Nur einen Unter, der nicht der Eichelunter ist?
        stiche = stiche + 2                 # Sehr, sehr kritisch

    elif u == 0:                            # Keinen Unter?
        stiche = stiche + 4                 # laestig. 4 Trumpfstiche im Worst :c

    if u < 4 and pos != 0:                  # Nicht erster und nicht alle 4 Unter?
        stiche = stiche + 1                 # Einer koennte dir zusaetzlich abgehen...
        if stiche > 4:
            stiche = 4

    # Nun die Farbstiche

    return -1


print ( Anfang( [4, 8, 19, 24, 26, 27, 30, 31], 0 ) )