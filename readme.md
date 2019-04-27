# La chasse au monstre

## Pr�sentation

La chasse au monstre est un jeu se jouant a 0, 1 joueur contre l'ordinateur ou 2 joueurs en duel.

Il existe deux r�les jouables :
- Le chasseur
- Le monstre

Les deux joueurs apparaissent sur un plateau � deux endroits diff�rant.

Chaque joueur a un objectif propre :
	
- La b�te doit parcourir toutes les cases du plateau tout en ayant comme contrainte de ne pouvoir passer
qu'une seule fois sur chaque case du plateau. Elle a sa disposition un nombre de teleportation afin de l'aider en case
de blocage.

- Le chasseur lui doit trouver la b�te avec pour seule indiction, le temps �coule entre le passage de la b�te 
et celui du chasseur sur les cases ou le chasseur passe.

### Les Bonus

Pour pimenter les parties nous avons �galement rajouter des objets collectable par les joueurs, pour aider � accomplir 
son objectif ou alors handicaper son adversaire. Nous avons �galement pr�vu plusieurs plateaux plus ou moins complexe 
pour ajouter de la difficult� et de la re jouabilit�.

La disposition de ces pieges est situe sur la distance moyenne de chaque cote du plateau.

Voici la liste des objets disponible ainsi qu'un petit guide de leur utilisation :

- Les pieges

Seul le chasseur peut utiliser cet objet, le piege n'est pas visible par le monstre. Une information avec la position du piege
lorsqu'il est activ est disponible pour le chasseur si un intru se presente sur celle-ci. Le piege disparait ensuite.
La bete se voit privee de ses deplacements le tour suivant du deplacement sur la cage du piege.

- La balise de vision 

Seul le chasseur peut utiliser cet objet, elle eclaire une partie du plateau suivant la formule (taille/10) arrondi a
l'entier superieur.

- Le camouflage

Seul le monstre peut utiliser cet objet, elle octroie a la bete la capacite d'effacer ses traces de pas a condition.
De ce fait, elle ne peut pas se deplacer durant ce tour.

- Les leurres

Seul le monstre peut utiliser cet objet, le monstre pose une copie de lui sur une case qui reste jusqu'au moment ou le chasseur passe
sur le casse ou est situe le leurre. Le leurre n'a pas de limite de temps.

## Utilisation

Lancer le jeu :

```
jeu.jar --width=20 --height=20 --tp=3 --gamemode=beast --trap --camouflage --ward --bait
```

Voici les arguments disponible :

```
--width=XX           Modifie la largeur du plateau
--height=XX          Modifie la hauteur du plateau
--tp=XX              Indique le nombre de teleportation disponible pour le monstre durant une partie.
--gamemode=ZZ        Selection du mode de jeu avec pour ZZ les valeurs disponibles suivantes :

ai        : L'ordinateur joue contre lui meme et controle les deux entites.
beast     : Le joueur controle le monstre et l'ordinateur controle le chasseur.
hunter    : Le joueur controle le chasseur et l'ordinateur controle le monstre.
multi     : Un des deux joueurs controle le monstre et son adversaire controle le chasseur.

--trap               Active les pieges sur le plateau
--camouflage         Active les camouflages sur le plateau
--ward               Active les balises de vision sur le plateau
--bait               Active les leurres sur le plateau
```

Voici les arguments lances par defaut :


```java
jeu.jar --width=11 --height=11 --tp=3 --gamemode=beast 
```
	
## Developpement

Trello est l'outil qui nous permet de definir les futures taches a repartir parmi les membres de l'equipe.
L'outil nous permet de nous definir des sous-jalons 
[Lien](https://trello.com/b/s4kqkszi/gdp-s2).

Journal de bord du projet et premiere reflexion sur l'architecture du projet : 
[Lien](https://docs.google.com/document/d/1dkxMliuccb0sJ5Aa55jqOZho3DUbD6qv2GQvYVswOF4/edit?usp=sharing)

Estimation des heures sur le projet :
[Lien](https://docs.google.com/spreadsheets/d/1WuWRMOKtEFklpOo74Tn_29-D8dcVBh5htOClASj9DFM/edit#gid=1856470417)

<div align="center">
  <a href="https://www.youtube.com/watch?v=yJjANTtQ66s"><img src="https://img.youtube.com/vi/yJjANTtQ66s/0.jpg" alt="Video resume du developpement"></a>
</div>

## Contributeurs
- FORESTAL Virgil (diazw)
- PEIRERA Nathanael (nath)
- MAYEUX Pierre (PHPierre)
- PROGNON Quentin (prognonq)