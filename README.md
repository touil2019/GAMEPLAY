# GAMEPLAY

Escape Game ONLINE

Projet en lien avec le jeu Escape Game avec la mise en place de la brique “mécanisme de recherche d’une combinaison à X chiffres”.

3 modes de Jeux:


Mode Challenger:

L'intelligence artificielle de l’ordinateur joue le rôle de défenseur. Elle définit une combinaison de X chiffres aléatoirement.
Le joueur a le rôle d’attaquant et doit faire une proposition d’une combinaison de X chiffres.
L'intelligence artificielle de l’ordinateur indique pour chaque chiffre de la combinaison proposée si le chiffre de sa combinaison est plus grand (+), plus petit (-) ou si c’est le bon (=).
Il y a un nombre limité d’essais.

défenseur:

Le joueur (cette fois dans le rôle de défenseur) définit une combinaison de X chiffres aléatoirement.
L'intelligence artificielle de l’ordinateur doit faire une proposition d’une combinaison de X chiffres (c’est le rôle attaquant).
Le joueur indique pour chaque chiffre de la combinaison proposée si le chiffre de sa combinaison est plus grand (+), plus petit (-) ou si c’est le bon (=).
L’intelligence artificielle fait une autre proposition en se basant sur la réponse fournit par le joueur.
Il y a un nombre limité d’essais.

duel:

Le joueur et l’intelligence artificielle de l’ordinateur jouent tour à tour. Le premier à trouver la combinaison secrète de l'autre a gagné.

FONCTIONNALITÉS SUPPLÉMENTAIRES :

MODE DÉVELOPPEUR 
le mode développeur peut s'activer via l'argument et sa configuration. la seconde possibilité est par le fichier config manuellement en passant la valeurs de 0 à 1.

LOGS utilisation du fichier LOG4J2 avec remplacement de l'ensemble System.out avec prise en compte de deux niveau d'affiche info et error pour l'affichage des exceptions.

FICHIER DE CONFIGURATION
trois variables de renseigner dans le fichier de config:
la longueur C correspondANT à la longueur du tableau et aux nombres de chiffres de la combinaison par defaut.
le nombre d'essai corresponds au nombre tour de jeu .
le mode dev à 1 permet d'afficher le nombre recherché.
