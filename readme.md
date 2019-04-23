# La chasse au monstre

## Présentation

La chasse au monstre est un jeu se jouant a 1 joueur contre l'ordinateur ou 2 joueurs.

Il existe deux rôles jouables :
- Le chasseur
- Le monstre

Les deux joueurs apparaissent sur un plateau à deux endroits différant.

Chaque joueur a un objectif propre :
	
- La bête doit parcourir toutes les cases du plateau tout en ayant comme contrainte de ne pouvoir passer
qu'une seule fois sur chaque case du plateau.

- Le chasseur lui doit trouver la bête avec pour seule indiction, le temps écoule entre le passage de la bête 
et celui du chasseur sur les cases ou le chasseur passe.

Pour pimenter les parties nous avons également rajouter des objets collectable par les joueurs, pour aider à accomplir 
son objectif ou alors handicaper son adversaire. Nous avons également prévu plusieurs plateaux plus ou moins complexe 
pour ajouter de la difficulté et de la re jouabilité.

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