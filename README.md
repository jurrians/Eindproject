# projectMprog
Budgetteer-app

Jurrian Schreuder (11053542)

Problem statement

	Problem statement: 
	Veel studenten hebben moeite met het overzichtelijk houden van hun financiën en hun uitgaven te structureren, 
	waardoor er vaak nog maand over is aan het einde van hun geld. 

	Target audience:  
	Studenten; mensen tussen de 18 en 28 jaar oud die een studie of opleiding volgen aan de universiteit of 
	hogeschool en moeite hebben met het structureren van hun uitgaven. 


Solution

	Single sentence summary:
	Een app die automatisch uitgaven structureerd a.d.h.v. door de gebruiker gespecificeerde categorieëen, 
	waardoor studenten overzicht krijgen in hoeveel geld zij nog te besteden hebben aan bijv. eten of biertjes. 

	Visual sketch: 
  
  
  ![](doc/IMG_0020.JPG)
  ![](doc/IMG_0021.JPG)
  ![](doc/IMG_0022.JPG)
    
  
    Main features: 
	-	Haalt automatisch uitgaven en inkomsten van een gebruiker op van hun bankrekening.
	-	Laat de gebruiker zelf categorieen maken waarin zij hun uitgaven kunnen indelen.
	-	Laat de gebruiker prioriteits-uitgaven (bijv. de huur) definiëren, welke automatisch gealloceerd worden als niet-		 besteedbaar geld in hun balans.
	-	Laat de gebruiker per categorie welk percentage van hun (non-priority) geld zij kunnen besteden in de vorm van een 		   horizontale ‘bar-chart’
	
    Minimum Viable Product: 
	Het MVP bestaat uit de bovenstaande features. Mogeijke toevoegingen aan de app luiden als volgt:  
	-	Laat de gebruiker meerdere rekeningen aan hetzelfde overzich toevoegen. 
	-	Laat de gebruiker een geschiedenis zien van hun transacties; in totaal en per categorie. 



Prerequisites
	
	Data sources: 
	Het Transaction- gedeelte van de ‘Open Bank Project’- API, waarbij transacties van een of meerdere bank-accounts worden 	opgevraagd. 
	https://apiexplorer.openbankproject.com/?version=3.0.0&list-all-banks=false&core=&psd2=&obwg=&ignoredefcat=&bank_id=&account_id=&view_id=&counterparty_id=&transaction_id=#vv1_2_1-getOtherAccountForTransaction

	Data wordt opgehaald als een JSON, die vervolgens gelinkt moet worden aan door de app gegenereerde categorieëen etc.
	Hiervoor zal een FireBase database gebruikt worden. 
	
	
	External components: 
	GraphView - open source graph plotting library for Android. Zal gebruikt worden om de visualisaties te maken in de balans; 
	hoeveelheid geld uitgegeven per categorie, besteedbaar geld per categorie, etc. Verder kan deze library ook gebruikt worden om 
	de visualisaties van de transactiegeschiedenis per categorie te maken
	
	
	Similar mobile apps: 

	ABN Grip-app
		
	“Grip is gekoppeld aan de app Mobiel Bankieren van ABN Amro en geeft inzicht in je uitgavenpatroon. 
	Waar geef je veel geld aan uit, en waaraan weinig?”

	    	What does it do?
			
	“Grip is een digitaal huishoudboekje en geeft inzicht in je uitgavenpatroon. 
	De app analyseert waar je veel en weinig aan uitgeeft in vergelijking met gemiddelden van het Nibud. 
	Ook vergelijkt de app je eigen uitgaven over verschillende perioden. 
	De analyses kun je verfijnen door de uitgaven beter te categoriseren of te taggen.”

	    	How have they implemented it?
			
	Aangezien het een app voor gebruikers van ABN is, linkt ABN de Grip-app aan de app Mobiel Bankieren. 
	Hierdoor krijgt de Grip app toegang tot de transacties van een bepaalde bankrekening. Deze worden vervolgens 

	    	Can you do it the same way?
			
	Aangezien de app die voor dit project gemaakt wordt niet direct gelinkt wordt aan een mobiel bankieren app van een specifieke bank,
	zal de data op een andere manier dan bij de abn verzameld worden; namelijk, via de Open Bank Project API. 
	Dit heeft als voordeel dat deze app meerdere bank-accounts in één overzicht kan meenemen.  
	
	
	
	Technical problems & limitations:
	De Open Bank Project-API is alleen toegankelijk in een sandbox, waardoor er alleen gewerkt kan worden met ‘fake’ data. 
	Mocht dit problemen veroorzaken zou er eventueel nog gekeken kunnen worden naar de ‘Account Information API’ van de ING: 
	https://developer.ing.com/api-marketplace/marketplace/b6d5093d-626e-41e9-b9e8-ff287bbe2c07/overview

	
