# ExempleMethodologieGL
exemples (event, thread) pour le cours Méthodologie GL L3 Miage de l'Unviersité Côté d'Azur

Soit vous ouvrez le code dans votre ide, soit vous pouvez faire en ligne de commande 
```
mvn compile 
#pour la démo swing / event graphique
mvn exec:java 
#pour le thread, avec pas le temps de calculer ou avec erreur de calcul : 
mvn exec:java@thread01
mvn exec:java@thread02
#pour le thread avec une version synchro
mvn exec:java@thread04
#pour wait / notify
mvn exec:java@wait
```
