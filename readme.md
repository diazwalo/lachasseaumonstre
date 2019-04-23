# La chasse au monstre

## Pr�sentation

La chasse au monstre est un jeu se jouant a 1 joueur contre l'ordinateur ou 2 joueurs.

Il existe deux r�les jouables :
- Le chasseur
- Le monstre

Les deux joueurs apparaissent sur un plateau � deux endroits diff�rant.

Chaque joueur a un objectif propre :
	
- La b�te doit parcourir toutes les cases du plateau tout en ayant comme contrainte de ne pouvoir passer
qu'une seule fois sur chaque case du plateau.

- Le chasseur lui doit trouver la b�te avec pour seule indiction, le temps �coule entre le passage de la b�te 
et celui du chasseur sur les cases ou le chasseur passe.

Pour pimenter les parties nous avons �galement rajouter des objets collectable par les joueurs, pour aider � accomplir 
son objectif ou alors handicaper son adversaire. Nous avons �galement pr�vu plusieurs plateaux plus ou moins complexe 
pour ajouter de la difficult� et de la re jouabilit�.

## Utilisation

Lancer le jeu :

```
jeu.jar --width=20 --height=20 --gamemode=beast
```

Voici les arguments disponible :

```
--width=XX           Modifie la largeur du plateau
--height=XX          Modifie la hauteur du plateau
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
	
## Developpement 

<div align="center">
[![Video resume du developpement](https://img.youtube.com/vi/yJjANTtQ66s/0.jpg)(https://www.youtube.com/watch?v=yJjANTtQ66s)
</div>

## Contributeurs
- FORESTAL Virgil (diazw)
- PEIRERA Nathanael (nath)
- MAYEUX Pierre (PHPierre)
- PROGNON Quentin (prognonq)