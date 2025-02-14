<div align="center">
	<b style="font-size: 2.5em;">JPokeBattle</b>
	<br>
	Adan Formisano 1813917
</div>

# Indice
- [Indice](#indice)
- [JPokeBattle](#jpokebattle)
  - [Risorse](#risorse)
  - [Features Implementate](#features-implementate)
- [Descrizione Generale](#descrizione-generale)
  - [Statistiche Pokémon](#statistiche-pokémon)
  - [Battaglia tra Pokémon](#battaglia-tra-pokémon)
    - [Ordine di Esecuzione](#ordine-di-esecuzione)
    - [Target](#target)
    - [Calcolo dei Danni](#calcolo-dei-danni)
- [Implementazione](#implementazione)
  - [Game Engine](#game-engine)
  - [Applicazione GUI (JavaFX)](#applicazione-gui-javafx)
  - [Diagramma delle classi](#diagramma-delle-classi)
- [Decisioni](#decisioni)
- [Risorse esterne](#risorse-esterne)
- [Design Pattern](#design-pattern)
- [Applicazione di paradigmi di programmazione](#applicazione-di-paradigmi-di-programmazione)


# JPokeBattle
*JPokeBattle* è un progetto che implementa una versione ridotta del sistema di battaglie del videogioco *Pokémon*, in particolare questa implementazione cerca di seguire il più possibile i videogiochi della 3ª Generazione.

Questo progetto è stato sviluppato da una singola persona, come progetto d'esame per *Metodologie di Programmazione* e segue la [traccia](./progetto%20MDP%20A-L%2023_24.pdf) fornita dal professore.

La repository e presente su [GitHub](https://github.com/AdanFormisano/JPokeBattle).

## Risorse
- Java JDK 23
- JavaFX
- [Bulbpedia](https://bulbapedia.bulbagarden.net/wiki/Main_Page) - Utlizzato per le statistiche del gioco e altre informazioni relative ai giochi *Pokémon*
- [The Cave of Dragonflies](https://www.dragonflycave.com/) - Utilizzato come fonte di informazioni dettagliate sulle meccaniche di gioco da implementare
  

## Features Implementate
L'mplementazione realizza **Feature - Comuni** e **Feature - Sviluppatore Singolo** di livello **Minimo**, **Tipico** ed **Extra** (*Set Crescita* e *Set Botness* ???).
- **Feature - Comuni**
  - *Minimo*
    - Implementare Bulbasaur, Charmander, Squirtle
    - Implementare statistiche base dei pokémon
    - Assegnare a tutti i Pokémon le due mosse di tipo neutro apprese al livello 1 (growl e tackle per Bulbasaur, growl e scratch per Charmander, tail whip e tackle per Squirtle)
    - Implementare le schermate “start”, battaglia, cambio pokémon, “you win”, e “game over”
  - *Tipico*
    - Implementare tutte le mosse dei Pokémon scelti, rispettando le loro meccaniche di funzionamento dipendenti dai loro tipi, ma ignorando i cambiamenti di stato come avvelenamento, stordimento, etc.
  - *Extra - Set Crescita*
    - Implementare punti individuali e punti allenamento che migliorino le capacità dei pokémon sulla base delle vittorie, aggregandoli appropriatamente
    - Implementare i meccanismi di passaggio di livello ed evoluzione dei Pokémon, incluso l’apprendimento di nuove mosse (rispettando il vincolo delle 4 mosse a disposizione di ciascun pokémon nella lotta: l’allenatore deve decidere, nel momento in cui una nuova mossa diventa disponibile ed il pokémon già ne conosce 4, se apprendere la nuova dimenticandone una delle precedenti a scelta)

- **Feature - Sviluppatore Singolo**
  - *Minimo*
    - Far affrontare al giocatore una serie di avversari NPC, fino alla sua prima sconfitta
  - *Tipico*
    - Preservare lo stato dei pokémon del giocatore nella serie di lotte (e.g., se un Pokémon riceve 10 PS di danno nella prima lotta, partirà con 10 PS in meno nella seguente, etc.)
    - Implementare una schermata leaderboard che mantenga i 10 record migliori (numero di lotte vinte in serie)
  - *Extra - Set Botness* ???
    - Implementare strategie per un comportamento “intelligente” degli avversari NPC, per supportare un’esperienza di gioco appagante


# Descrizione Generale
In questa implementazione semplificata di *Pokémon*, viene per prima cosa presentata all'utente la classica scelta degli *starters* della prima generazione (Bulbasaur, Charmander, Squirtle). Una volta eseguita la scelta iniziale viene generata una battaglia contro un pokémon scelto casualmente.

Ogni battaglia viene generata casualmente, i pokémon che possono essere scelti sono i 3 starters e ognuna delle loro 3 evoluzioni. Ogni volta che l'utente è riesce a sconfiggere il pokémon, o tutti i pokémon, della battaglia corrente, la prossima battaglia verrà generata con pokémon incrementati di un livello (e.g. al livello 5 si affrontano pokémon di livello 5, se si supera tale livello i pokémon generati saranno di livello 6). Questo *game loop* continua fino a quando l'untente non ha più alcun pokémon da utilizzare in una battaglia.

Quando l'utente viene sconfitto, gli viene presentato un resoconto della partita, con la sua squadra di pokèmon, il pokèmon da cui è stato sconfitto, e una lista di battglie/pokémon contro cui ha vinto fino a quel punto. Inoltre, l'utente può decidere di aprire e visualizzare la classifica delle 10 migliori partite che ha giocato.

## Statistiche Pokémon
Come già menzionato, l'implementazione dei pokémon segue quella della 3ª Generazione, ma leggermente semplificata, in particolare le statistiche vengono calcolate secondo la [formula](https://www.dragonflycave.com/mechanics/stats):
$$
Stat = floor(floor((2*B + I + E) * L / 100 + 5) * N)
$$

Invece, per gli *Hit Points*
$$
Stat = floor((2 * B + I + E) * L / 100 + L + 10)
$$

Dove i simboli sono,
- $B$: Statistiche base, dalla 3ª Generazione in poi vengono aggiunti anche *Special Attack* e *Special Defense*.
- $I$: *Individual Value*, una per ogni statistica base, con range di valori $[0, 31]$, vengono però ignorate le [caratteristiche](https://bulbapedia.bulbagarden.net/wiki/Individual_values#Characteristic) dei pokémon.
- $E$: *Effort Value*, una per ogni statistica base, con range di valori $[0, 255]$; a differenza delle generazioni precedenti, in questa ogni specie pokémon ha un valore che "cede" al pokémon da cui viene sconfitto  (vengono tralasciati gli altri metodi di acquisizione dei punti EV, e.g. vitamine, EXP Share, ecc.).
- $L$: livello attuale del pokémon.
- $N$: la [natura](https://bulbapedia.bulbagarden.net/wiki/Nature) del pokémon.

## Battaglia tra Pokémon
Le duelli che l'utente esegue durante una partita seguono le classiche regole dei videogiochi, ignorando però gli effetti di stato (come indicato dalla traccia del progetto); in particolare, è seguita la tabella dei tipi della 3ª generazione

<div align="center">
	<img src="./images/Screenshot%202025-02-12%20225040.png" style="width: 75%">
</div>

### Ordine di Esecuzione
L'ordine con cui avviene un turno è:

1. Vengono scelte le mosse da eseguire in questo turno
2. Vengono confrontate le priorità delle mosse scete dall'utente e dal pokémon avversario
3. In caso di parità ugale tra le due mosse, viene calcata la velocità dei pokémon in battaglia: il più veloce sarà il primo ad eseguire la propria mossa
4. Viene verificato che la mossa abbia sucesso ed effettivamente riesca a colpire l'avversario, in caso il colpo machi il bersaglio viene eseguita la mossa dell'avversario
5. Se la mossa colpisce, vengono calcolati e applicati i danni della prima mossa
6. Nel caso in cui il primo pokémon colpito non sia svenuto, allora viene eseguita anche la sua mossa
7. Viene controllato se è svenuto qualche pokémon
8. Nel caso in cui sia svenuto un pokémon, e il "propetario" abbia ancora pokémon a disposizione, viene mandato in campo il primo pokémon disponibile
9. Terminata la battaglia, se il giocatore ha vinto, vengono distribuiti gli EV e gli EXP

### Target
A differenza della 3ª Generazione, dove sono state aggiunte le battaglie di coppia, in questa implementazione le battaglie sono solo 1v1

### Calcolo dei Danni
I danni subiti da un Pokémon vengono calcolati con la seguente [formula](https://bulbapedia.bulbagarden.net/wiki/Damage#Generation_III), 
$$
    Damage=\Biggl(\Biggl(\frac{(\frac{2 \times Level}{5} + 2)\times Power \times \frac{A}{D}}{50}\Biggl) + 2 \Biggl)\times \text{STAB} \times \text{Type1} \times \text{Type2} \times \text{random}
$$

Dove i simboli sono,
- $Level$: Livello del Pokémon attaccante.
- $Power$: L'effettiva potenza della mossa.
- $A$: L'effettiva statistica di *Attacco* del Pokémon attaccante, se la mossa è di tipo *fisico*, altrimenti se la mossa è una *mossa speciale* la statistica coincide con *Special Attack*.
- $D$: L'effettiva statistica di *Difesa* del Pokémon difensore, se la mossa è di tipo *fisico*, altrimenti se la mossa è una *mossa speciale* la statistica coincide con *Special Defense*.
- $\text{Crit}$: ???
- $\text{STAB}$: É un bonus pari a $1.5$ nel caso in cui il tipo della mossa sia uguale ad uno dei due tipi del Pokémon attaccante, vale $1$ altrimenti.
- $\text{Type1}$ e $\text{Type2}$: Sono i valori di "efficacia del tipo" delle mossa contro il primo e secondo tipo del pokémon difensore.
- $random$: Viene calcolato mediante la moltiplicazione per un numero intero casuale, distribuito uniformemente tra $[85, 100]$, seguita da una divisione intera per $100$.


# Implementazione
Il progetto è strutturato in due macro-sezioni:
  - **Game Engine**: gestisce integralmente tutte le meccaniche di gioco, definendo la logica e il funzionamento interno del sistema.
  - **Interfaccia Utente**: si occupa dell'interazione tra l'utente e il game engine, offrendo una GUI e implementando i meccanismi necessari per la comunicazione bidirezionale tra le due componenti.

## Game Engine
Il *"game engine"* che gestisce le meccaniche di gioco è in grado di gestire la creazione ed esecuzione delle battaglie tra pokémon, generando ogni volta che sia necessario una nuova battglia

GameController
SessionData (GameData)
Battle
Interfacce usate per cominicare con GUI

## Applicazione GUI (JavaFX)


## Diagramma delle classi

# Decisioni

# Risorse esterne
- *Jackson* come Deserializer di file json

# Design Pattern
- Vengono utilizzati:
  - *signleton*
  - *observers*
    - Utlizzando principalmente interfacce e le Properties di JavaFX

# Applicazione di paradigmi di programmazione
- Paradigma principale è la programmazione a oggetti assecondata da *observers* che permettono la comunicazione tra gli oggetti implementati
- Alcuni elementi sono reattivi questo permette di avere elementi dinamici che cambiano


