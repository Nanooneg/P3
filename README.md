# P3 - DA JAVA - OpenClassrooms - Arnaud Laval


## Présentation :
### Ce programme contient le jeu du + ou - .
Le but est de trouver la combinaison proposée par l'autre joueur.

Il comporte 3 modes de jeu :

 - Challenger : vous devez trouver la combinaison de l'ordinateur.
 - Défenseur : L'ordinateur essai de trouver votre combinaison.
 - Duel : Avec l'ordinateur, à tour de rôle, vous essayer de décrouvrir vos combinaisons respectives.


## Développement :
### Ce programme a été développé avec IntelliJ IDEA 2019 et la Java JDK 11 (11.0.2).

Utilisation de Log4J pour générer les logs.


## Installation :
### Si ce n'est pas encore fait, importez l'emsemble du répertoire P3 depuis GitHub.

Le code source est contenu dans "src/main/java" et les ressources dans "src/main/ressources".
Il est IMPORTANT que le dossier "ressources" soit déclaré comme fichier Ressource.
Il contient 2 fichiers indispensable à la compilation du programme :

 - config.properties : contient les paramètres du jeu ( Vous pouvez changez ces paramètres dans ce fichier
avant de lancer le jeu : Nombre de chiffre de la combinaison / nombre de coup maximum / mode développeur ).

 - Log4j2.xml : des logs ont été ajoutés au code. A chaque lancement du programme, ils sont générés dans le fichier
de log "all.log". Vous pouvez consulté les informations sur le déroulement du programme (dernier lancement seulement).


## Lancement :
### Windows : dans le dossier "PlusOuMoins", vous pouvez lancer le programme en cliquant sur "P3.bat".
Vous pouvez lancer le programme en mode Développeur en cliquant sur "P3 - Mode développeur.bat"
Quand le programme est lancé en mode développeur, il affiche la solution à chaque fois qu'il demande à un joueur
de donner une réponse. Cela sert à tester le bon fonctionnement, ou à battre plus facilement l'ordinateur...
Il est également possible de faire comme cela : voir ci-dessous.

### Pour Mac et Linux, exécutez le fichier "P3.jar".
Pour cela, depuis le terminal, rendez-vous dans le dossier "PlusOuMoins" et tapez la commande : java -jar P3.jar
Vous pouvez passez en argument "true" pour lancer le programme en mode développeur : java -jar P3.jar "true"


## Version 1.0

## Autheur : NanoO

