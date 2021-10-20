package util;

public class Voie implements Velo, Voiture{
    private final String name;
    private final int level;
    private int vehicule[];     //position des vehicule dans la voie.
    private int flag = (int)(Math.random() + 1);
    private int vehiculeInterface[];

    public Voie(String name, int level, int taille)
    {
        this.name = name;
        this.level = level;
        this.vehicule = new int[taille];
        this.vehiculeInterface = new int[]{idVelo, idVoiture};
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

    public void setVehicleCoef(int coef, int i)
    {
        this.vehicule[i] = coef;
    }

    private Boolean passage()
    {
        /*
         * passage indique si un nouveau vehicul apparait dans la voie.
         * Renvoie True si un vehicule apparait, sinon False.*/
        double d = Math.random();

        return d < 0.2;
    }

    public void avancerVelo(int i)
    {
        if(i == this.vehicule.length - 1)       //A modifier.
        {this.vehicule[i] = 0;}
        else
        {
            for(int k = tailleVelo; k > 0; k--)
            {
                this.vehicule[i + k] = this.vehicule[i + k - 1];
            }

            this.vehicule[i] = 0;
        }

    }

    public void avancerVoiture(int i)
    {
        if (i == this.vehicule.length - 1)      //A modifier.
        {this.vehicule[i] = 0;}
        else if(i == this.vehicule.length - 2)
        {
            this.vehicule[i] = 0;
            this.vehicule[i + 1] = 2;
        }
        else
        {
            for(int k = tailleVoiture; k > 0; k--)
            {
                this.vehicule[i + k] = this.vehicule[i + k - 1];
            }

            this.vehicule[i] = 0;
        }
    }

    private void avancer(int debut)
    {
        /*
         * avaner permet de mettre a jour la voie en avancant les vehicules d'une case.
         * Renvoie (void).*/

        for(int i = debut; i < this.vehicule.length; i++)       //Avancer vehicule existant.
        {
            switch (vehicule[i])
            {
                case idVelo:
                    avancerVelo(i);
                    i += tailleVelo;
                    break;
                case idVoiture:
                    avancerVoiture(i);
                    i += tailleVoiture;
                    break;

            }
        }
    }

    public void unTourVoie()
    {
        /*
         * unTourVoie permet de faire avance la partie d'un coup d'horloge en appelant les methodes de mises a jour de la classe.
         * Renvoie (void).*/
        if(this.flag == 1)
        {
            avancer(0);
            this.vehicule[0] = 0;
            this.flag -= 1;
        }
        else if(this.flag == 2)
        {
            avancer(0);
            this.vehicule[0] = this.vehicule[1];
            this.flag -= 1;
        }
        else if(this.vehicule[0] == 0 && passage())
        {
            this.flag = (int)(Math.random() * this.vehiculeInterface.length) + 1;
            this.vehicule[0] = this.vehiculeInterface[flag - 1];
            avancer(1);
        }
        else
            avancer(0);


    }
}
