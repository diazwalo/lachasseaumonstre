# La chasse au monstre

## Présentation

La chasse au monstre est un jeu se jouant a 0, 1 joueur contre l'ordinateur ou 2 joueurs en duel.

Il existe deux rôles jouables :
- Le chasseur
- Le monstre

Les deux joueurs apparaissent sur un plateau à deux endroits différant.

Chaque joueur a un objectif propre :
	
- La bête doit parcourir toutes les cases du plateau tout en ayant comme contrainte de ne pouvoir passer
qu'une seule fois sur chaque case du plateau. Elle a sa disposition un nombre de teleportation afin de l'aider en case
de blocage.

- Le chasseur lui doit trouver la bête avec pour seule indiction, le temps écoule entre le passage de la bête 
et celui du chasseur sur les cases ou le chasseur passe.

Pour pimenter les parties nous avons également rajouter des objets collectable par les joueurs, pour aider à accomplir 
son objectif ou alors handicaper son adversaire. Nous avons également prévu plusieurs plateaux plus ou moins complexe 
pour ajouter de la difficulté et de la re jouabilité.

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
jeu.jar --width=20 --height=20 --gamemode=beast
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
	
## Developpement 

<div align="center">
  <a href="https://www.youtube.com/watch?v=yJjANTtQ66s"><img src="https://img.youtube.com/vi/yJjANTtQ66s/0.jpg" alt="Video resume du developpement"></a>
</div>

## Contributeurs
- FORESTAL Virgil (diazw)
- PEIRERA Nathanael (nath)
- MAYEUX Pierre (PHPierre)
- PROGNON Quentin (prognonq)