# L3 design pattern report

- **Firstname**: Théo
- **Lastname**: DADON


> Add your thoughts on every TP bellow, everything is interresting but no need to right a book.
> 
> Keep it short simple and efficient:
> 
> - Ce que vous avez fait et pourquoi
> - Ce qui vous a aidé et pourquoi
> - Ce que vous avez trouvé difficile
> - Ce qui ne vous a pas aidé
> - Ce que vous avez eu besoin de changer
> - Tout ce qui est pertinent
> 
> Add a link to schemas describing your architecture (UML or not but add a legend)
> 
> Remember: it is ok to make mistakes, you will have time to spot them later.
> 
> Fill free to contact me if needed.

---
- **TP 1**: 
> - Restructuration du code avec l'ajout de classes pour une meilleure lisibilité et architecture.
> - Ce qui m'a principalement aidé est mon collègue Sacha et quelques tutos sur internet. Je demande car je suis un peu nul en programation mais je suis plutôt bon en architecture.
> - Le plus gros problème que j'ai rencontré est d'adapter le code à la restrucuration que j'ai faite.
> - Ma connaissance approximative de Java
> - Ce que j'ai du changer est le code pour l'adapter. Cependant, je n'ai pas cherché à l'optimiser énormément.
> - J'ai galéré à compiler car je savais pas mettre les arguments mais j'ai réussi. De plus, j'ai du réadapter le GhostTest pour qu'il fonctionne avec mon architecture.
> - Pour le diagramme, je l'ai fait en mermaid. Le fichier est [ici](diag.mmd) ou sinon cliquez sur ce [lien](https://mermaid.live/edit#pako:eNrFVF1rwjAU_SshTxva_oAiheEUlK0OKmOwjhGaaw1rEklS2XD-96WtztRGnwbLS8u9555zv7g7nEsKOMJ5SbS-Z6RQhGcC2ddY0N1mg3atoX4DTpi4SY1ionh9Q0QV-hZFaCsZbUH7TLjhY8k5EXQmDKgVycHlCuAT8spAn44J42VbSiofmDaPRJAClEs2GrGjRhw7-TKhQZmDBDKWoJtvAyot583VOuZaCo9qsGIlPBGztrH15--Fx3r7L7oTpaBSHunB2s6zBOuX6qjCQWuLu0o4tRn76Aow00MxR7q6uITwhu9U3XmCwteYebpI3icvy0mSzhaJjW8pT4Bx-nzR_6tQL30QxL3tbb29na6hZ6vZIt2tCcPvCyhnxFdAPtHOkPqSNcRpe0_N7z8XCsPYabavsL6_U1LXjYeYg7JnhNqr00wuw2YNHDIc2V9K1EeGM7G3OFIZmX6JHEdGVTDE1YYSA4cj1Rr3P--dfq0) pour le voir en ligne.

---

- **TP 2**:
> - J'ai un peu refondu le code pour éviter au maximum les static. J'ai aussi remis execute dans le main ce qui explique la suppression de CommandeLigneInterface.
J'ai mis en place FileFormatManager qui me permet de gérer les différents formats de fichier. J'ai appliqué le plus possible le concept de SOLID.
> - Je commence a plus me familiariser avec les concepts de Java et je commence à comprendre comment fonctionne le code. Cependant, j'utilise encore beaucoup les forums et les tutos.
> - Encore une fois, la principale difficulté est la connaissance approximative de Java. Je concède le fait de demander à ChatGPT pour m'aider à comprendre certaines choses et qu'il me donne des exemples plus concrets.
> - La connaissance approximative de Java.
> - J'ai du changer le code pour l'adapter à ma nouvelle logique même si elle ne change pas réellement de la version précédente.
> - Je vais mettre en place une fabrique pour les formats de fichier pour que cela soit plus simple a rajouter des extensions sans trop toucher au code.
> - Pour le 2ème diagramme, le fichier est [ici](diag2.mmd).

---

- **TP 3**:
> - J'ai essayé de refactorer le code pour qu'il corresponde à vos attentes dans le mail, surtout avec les concepts SOLID qui me manquaient.
> - Encore une fois, le collègue Sacha mais beaucoup les forums et les vidéos.
> - Le concept SOLID est vachement complexe si on essaie de faire quelque chose de qualité. J'ai du mal à comprendre comment l'appliquer parfois.
> - La connaissance moyenne de Java. (je suis passé d'approximative à moyenne)
> - J'ai principalement changé les fichiers TodoList et Todo puis j'ai crée des fichiers pour insérer et lister au moins c'est indépendant et facilement modifiable
> - J'ai encore beaucoup à modifier je pense, mais il me semble que je progresse. Il ne faut pas oublier de mettre un auteur,une couleur et/ou une description !
> - Pour le 3ème diagramme, le fichier est [ici](diag3.mmd) ou en ligne [ici](https://mermaid.live/edit#pako:eNq9VtFu2jAU_RUrT1QjfECEkCY6pE6tNK19W_bgObfBWmJHttOto_z7rgOhtuOkoKnjAUHu8fG55_peZ5cwWUCSJayiWl9zWipa5yoXBD_dM_Kxaciuf2Q_KZN1TUXxFUqujXrOyNp_4II_1JSL2b1RXJTfvhOqSn2VkSfJCw8Fv4ENUFwYD8QFN5xW_A-gpoozargUs4Bu76sPpMUzWUuBwZYZqXRG7mjjbau6taCOVEeZc7JpBbMKYvmUYEJ4n93Vya9pyb7U5RLdAPVIGaxWA-taAzPfsIDzRmhQJsqcWrezk7zLuW_RnfdhfpCFtOx3VNAS1L8cwy0GKvBrEj1pgYQNr2AjVU3jIsbLwjvHbQazL9Rs510ysaNSoVgb0x0OETbjsaOhn6Iy3me3z1qK_7jdwOoNtR0Z9qw61XusUT2SU_-t7SYjvepjETTQMqE5bpECWtgozhYDwvT5H3bwkL8UNxBC-3kxPd1uZRnvjUqWltEyZcR-e9HCPGbkmhp44PUxTfOa4qFwspydo-CTUtCquAUNLjcIkOosKntOgjQK0Ezxxk7ZmHUpbc1WqmiIyWoY6Xbs_9iLLU1XgysiI60G3aPCqLMCkQzLbAZgsnxJ02Dm4pCpmwpqLHAc7o7RGNinWyxW4w3jZ-ASX7BsFGY5nEE0MGFyoTtThva9si4WLxGlUWNcyguWDVFWX3cIfSOGxh9BgfrwtjrjbL3ma8HuMPGBborTSDdikc6E6IAF-XG6GJ1Yfy7ipH2n-M0-5dJbaPdMvoWN-TqGT-ZJDVhTXuA77c6uzxOzxcLnSYY_C6p-5kku9ojD2SHvnwVLMnz1g3nSNgVW8_gKfHi4_wtTX6Bu).
> - Fait le 24/02


