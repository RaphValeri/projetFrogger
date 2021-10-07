package util;

public class Voie {
    //Rajouter la vitesse comme variable d'instance
    private final String name;
    private final int level;
    private int vehicule[];     //position des vehicule dans la voie.

    public Voie(String name, int level, int taille)
    {
        this.name = name;
        this.level = level;
        this.vehicule = new int[taille];
    }

    public String getName()
    {
        /*
         * getState permet d'acceder a la variable name.
         * Renvoie la variable name.*/
        return this.name;
    }

    public int getLevel()
    {
        /*
         * getState permet d'acceder a la variable level.
         * Renvoie la variable level.*/
        return this.level;
    }

    public int[] getVehicule()
    {
        /*
         * getState permet d'acceder a la variable vehicule.
         * Renvoie la variable vehicule.*/
        return this.vehicule;
    }

    public void setVehicule(int[] vehicule) {
        this.vehicule = vehicule;
    }

    public Boolean passage()
    {
        /*
         * passage indique si un nouveau vehicul apparait dans la voie.
         * Renvoie True si un vehicule apparait, sinon False.*/
        double d = Math.random();

        return d < 0.2;
    }

    private void avancer(int debut)
    {
        /*
         * avaner permet de mettre a jour la voie en avancant les vehicules d'une case.
         * Renvoie (void).*/
        for(int i = debut; i < this.vehicule.length; i++)       //Avancer vehicule existant.
        {
            if(this.vehicule[i] != 0)
                if(i == this.vehicule.length - 1)
                    this.vehicule[i] = 0;
                else
                {
                    this.vehicule[i + 1] = this.vehicule[i];
                    this.vehicule[i] = 0;
                    i++;
                }


        }
    }

    public void unTourVoie()
    {
        /*
         * unTourVoie permet de faire avance la partie d'un coup d'horloge en appelant les methodes de mises a jour de la classe.
         * Renvoie (void).*/
        if(this.vehicule[0] == 0 && passage())
        {
            this.vehicule[0] = 1;
            avancer(1);
        }
        else
            avancer(0);


    }

}
