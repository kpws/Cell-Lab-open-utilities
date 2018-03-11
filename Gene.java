// Cell Lab open utilities
// Copyright (C) 2018  Petter Säterskog

// This program is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.

// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.

// You should have received a copy of the GNU General Public License
// along with this program.  If not, see <http://www.gnu.org/licenses/>.


public class Gene{
	//Some cell properties
	public static final int N_GENES=40;//number of modes - "genes"
	public static final int N_SS=4;//number of signal substances
	public static final int MAX_NEIGHBORS=20;
	public static final float MAX_OUTPUT=20f;
	public static final int N_NEURO_OUT=4;


	public static final int FORMAT_VERSION=95; //when update, update to CL version number. If unchanged, keep lower version
	final public float color[] = new float[3];
	public float splitM, splitRatio, splitAngle, c1Angle, c2Angle, priority, linkStrength;
	public int c1gi, c2gi;//, virFrom, virTo;
	boolean makeLink, c1KeepLinks, c2KeepLinks,stayAlive,isInitial,mirror1,mirror2;
	public static final int gt;
    public class Value
    {
        float a,b,c;
        short i,type;
    };
    public static final int N_VALUES=11,N_INTS=2+2+1+N_NEURO_OUT+1+1,N_FLOATS=7;
    public final Value[] mValues=new Value[N_VALUES];
    public final int[] mInts=new int[N_INTS];
    public final float[] mFloats=new float[N_FLOATS];
    public static final int SWIM_FORCE_I=0;
    public static final int DENSITY_VAL_I =1;
    public static final int MUSCLE_BEND_VAL_I =2;
    public static final int MUSCLE_CONTRACT_VAL_I =3;
    public static final int MUSCLE_LIFT_VAL_I =4;
    public static final int STEM_THRESH1_VAL_I =5;
    public static final int STEM_THRESH2_VAL_I =6;
    public static final int GEN_SS_VAL_0_I =7;

    public static final int VIRUS_FROM_I=0;
    public static final int VIRUS_TO_I=1;
    public static final int SMELL_OUT_I=2;
    public static final int SMELL_TYPE=3;
    public static final int SECRETE_TYPE=4;
    public static final int GEN_SSID_0_I=5;
	public static final int MAX_CONNECTIONS_I=GEN_SSID_0_I+N_NEURO_OUT;
	public static final int TELOMERASE_LENGTH_I=MAX_CONNECTIONS_I+1;

    public static final int SECRETE_TYPE_FOOD_SMELL=0;
    public static final int SECRETE_TYPE_CYANIDE=1;
	public static final int SECRETE_TYPE_DEFENSIN=2;
	public static final int SECRETE_TYPE_PROTEASE=3;
	public static final int SECRETE_TYPE_COATED_FOOD_SMELL=4;
	public static final int SECRETE_TYPE_LIGHT_SMELL=5;
	public static final int SECRETE_TYPE_WALL_SMELL=6;
	public static final int SECRETE_NON_SS_TYPE_N=7;

    public static final int SMELL_AMP_FLOAT_I =0;
    public static final int SMELL_RED_FLOAT_I=1;
    public static final int SMELL_GREEN_FLOAT_I=2;
    public static final int SMELL_BLUE_FLOAT_I=3;
    public static final int SMELL_THRESH_FLOAT_I=4;
	public static final int ADHESIN_LENGTH_FLOAT_I=5;
	public static final int CYTO_SKELETON_FLOAT_I=6;
    //public static final int MAX_MASS_FLOAT_I=7;


    public static final int INT_MAX[]=new int[N_INTS];
    public static final float VAL_MIN[]=new float[N_VALUES];
    public static final float VAL_MAX[]=new float[N_VALUES];
    public static final float FLOAT_MIN[]=new float[N_FLOATS];
    public static final float FLOAT_MAX[]=new float[N_FLOATS];
	public static final int TELOMERASE_LENGTHS[]={-1,5,10,20,40,80};

	static final float CYTO_MAX=2f;//careful here, this is used in cell initializer but

    static {
        INT_MAX[VIRUS_FROM_I]=N_GENES;
        INT_MAX[VIRUS_TO_I]=N_GENES;
        INT_MAX[SMELL_OUT_I]=N_SS;
        INT_MAX[SMELL_TYPE]=6;
        INT_MAX[SECRETE_TYPE]=SECRETE_NON_SS_TYPE_N+2*N_SS;

        for(int i=0;i<N_NEURO_OUT;i++)
            INT_MAX[GEN_SSID_0_I+i]=N_SS;//+1+1+1+1;//mass, age, connections, mM

	    INT_MAX[MAX_CONNECTIONS_I]=MAX_NEIGHBORS;
	    INT_MAX[TELOMERASE_LENGTH_I]=TELOMERASE_LENGTHS.length;

        VAL_MIN[SWIM_FORCE_I]=0f;
        VAL_MAX[SWIM_FORCE_I]=1f;

        VAL_MIN[DENSITY_VAL_I]=-3f;
        VAL_MAX[DENSITY_VAL_I]=3f;

        VAL_MIN[MUSCLE_BEND_VAL_I]=-1f;
        VAL_MAX[MUSCLE_BEND_VAL_I]=1f;

        VAL_MIN[MUSCLE_CONTRACT_VAL_I]=-.5f;
        VAL_MAX[MUSCLE_CONTRACT_VAL_I]=.5f;

        VAL_MIN[MUSCLE_LIFT_VAL_I]=-1f;
        VAL_MAX[MUSCLE_LIFT_VAL_I]=1f;

        VAL_MIN[STEM_THRESH1_VAL_I]=-1f;
        VAL_MAX[STEM_THRESH1_VAL_I]=1f;
        VAL_MIN[STEM_THRESH2_VAL_I]=-1f;
        VAL_MAX[STEM_THRESH2_VAL_I]=1f;

        for(int i=0;i<N_NEURO_OUT;i++) {
            VAL_MIN[GEN_SS_VAL_0_I + i] =-MAX_OUTPUT;
            VAL_MAX[GEN_SS_VAL_0_I + i] =MAX_OUTPUT;
        }

        FLOAT_MIN[SMELL_AMP_FLOAT_I]=-3f*MAX_OUTPUT;
        FLOAT_MAX[SMELL_AMP_FLOAT_I]=3f*MAX_OUTPUT;
        FLOAT_MIN[SMELL_RED_FLOAT_I]=0f;
        FLOAT_MAX[SMELL_RED_FLOAT_I]=1f;
        FLOAT_MIN[SMELL_GREEN_FLOAT_I]=0f;
        FLOAT_MAX[SMELL_GREEN_FLOAT_I]=1f;
        FLOAT_MIN[SMELL_BLUE_FLOAT_I]=0f;
        FLOAT_MAX[SMELL_BLUE_FLOAT_I]=1f;
        FLOAT_MIN[SMELL_THRESH_FLOAT_I]=0f;
        FLOAT_MAX[SMELL_THRESH_FLOAT_I]=(float)Math.sqrt(3.);

	    FLOAT_MIN[ADHESIN_LENGTH_FLOAT_I]=0f;
	    FLOAT_MAX[ADHESIN_LENGTH_FLOAT_I]=0.01f;
	    FLOAT_MIN[CYTO_SKELETON_FLOAT_I]=.5f;
	    FLOAT_MAX[CYTO_SKELETON_FLOAT_I]=CYTO_MAX;
    }

	public Gene() {
        for(int i=0;i<N_VALUES;i++)mValues[i]=new Value();
	}

	//this function reads a genome file. Note that it returns a list with pointers to Gene objects, some of which may be
	// the same objects.. Better copy before editing them.
	static public Gene[] readGenome(ObjectInputStream in){
		int version = in.readInt();
		if(version>FORMAT_VERSION)throw new GenomeFormatException();

		
        Gene newJeans[]=new Gene[N_GENES];
        int readGenes=N_GENES;
        if(version<=17) readGenes=20;
        if(version<=3) readGenes=15;

		for(int i=0;i<readGenes;i++){
			 newJeans[i]=new Gene();
			 newJeans[i].readFromIS(in,version);
		}

        for(int i=0;i<N_GENES-readGenes;i++)
        {
            newJeans[readGenes+i]=new Gene(newJeans[0]);
            newJeans[readGenes+i].isInitial=false;
        }

        return newJeans;
	}

	//this funciton writes one mode to outputstream dest
	public void writeToOS(ObjectOutputStream dest) throws IOException {
        dest.writeInt(FORMAT_VERSION);
        dest.writeFloat(color[0]);
        dest.writeFloat(color[1]);
        dest.writeFloat(color[2]);
        dest.writeFloat(splitM);
        dest.writeFloat(splitRatio);
        dest.writeFloat(splitAngle);
        dest.writeFloat(c1Angle);
        dest.writeFloat(c2Angle);
        dest.writeFloat(priority);
        dest.writeInt(c1gi);
        dest.writeInt(c2gi);
        dest.writeBoolean(makeLink);
        dest.writeBoolean(c1KeepLinks);
        dest.writeBoolean(c2KeepLinks);
        dest.writeInt(gt);
        dest.writeInt(7);//lucky number
        dest.writeBoolean(stayAlive);
        dest.writeBoolean(isInitial);
        dest.writeBoolean(mirror1);
        dest.writeBoolean(mirror2);
        dest.writeFloat(linkStrength);
       
        for (int i = 0; i < N_VALUES; i++) {
            dest.writeShort(mValues[i].i);
	        dest.writeShort(mValues[i].type);
            dest.writeFloat(mValues[i].a);
            dest.writeFloat(mValues[i].b);
            dest.writeFloat(mValues[i].c);
        }
        for (int i = 0; i < N_INTS; i++) {
            dest.writeInt(mInts[i]);
        }
        for(int i=0;i<N_FLOATS;i++){
            dest.writeFloat(mFloats[i]);
        }
    }

    //this function reads a mode from input stream that is expected to be of genome version
    // "cellVersion".
	public void readFromIS(ObjectInputStream in, int cellVersion) throws IOException {
		int version;
		if(cellVersion>2)
			version=in.readInt();
		else
			version=1;
        //ZDebug.print("ver:"+version);
        float density=0f,swimForce=0f;
        int virFrom=0,virTo=0;
        if(version<7) {
            color[0] = in.readFloat();
            color[1] = in.readFloat();
            color[2] = in.readFloat();
            splitM = (float)in.readDouble();
            splitRatio = (float)in.readDouble();
            splitAngle = (float)in.readDouble();


            if (version == 5)
                c1Angle = (float)(in.readDouble() + Math.PI);
            else
                c1Angle = (float)in.readDouble();
            c2Angle = (float)in.readDouble();

            if(version<9){
                splitAngle+=Math.PI;
                c1Angle+=Math.PI;
                c2Angle+=Math.PI;
            }

            priority = (float)in.readDouble();
            c1gi = in.readInt();
            c2gi = in.readInt();
            makeLink = in.readBoolean();
            c1KeepLinks = in.readBoolean();
            c2KeepLinks = in.readBoolean();
            gt = in.readInt();
            in.readInt();
            stayAlive = in.readBoolean();

            density = (float) in.readDouble();
            if (version >= 3) {
                virFrom = in.readInt();
                virTo = in.readInt();
            } else {
                virFrom = 0;
                virTo = 0;
            }
            if (version >= 4) {
                isInitial = in.readBoolean();
                mirror1 = in.readBoolean();
                mirror2 = in.readBoolean();
                linkStrength = (float)in.readDouble();
            } else {
                isInitial = false;
                mirror1 = false;
                mirror2 = false;
                linkStrength = .0f;
            }
        }
        else
        {
            color[0]=in.readFloat();
            color[1]=in.readFloat();
            color[2]=in.readFloat();
            splitM = in.readFloat();
            splitRatio = in.readFloat();
            splitAngle = in.readFloat();
            c1Angle = in.readFloat();
            c2Angle = in.readFloat();

            if(version<9){
                splitAngle+=Math.PI;
                c1Angle+=Math.PI;
                c2Angle+=Math.PI;
                splitRatio=1f-splitRatio;
            }

            priority = in.readFloat();
            c1gi = in.readInt();
            c2gi = in.readInt();
            makeLink = in.readBoolean();
            c1KeepLinks = in.readBoolean();
            c2KeepLinks = in.readBoolean();
            gt = in.readInt();
            in.readInt();
            stayAlive = in.readBoolean();
            if(version<9){
                density=in.readFloat();
                virFrom=in.readInt();
                virTo=in.readInt();
            }
            isInitial= in.readBoolean();
            mirror1= in.readBoolean();
            mirror2= in.readBoolean();
	        if(version<16)
	            linkStrength= in.readFloat()*Link.MAX_STIFFNESS;
	        else
		        linkStrength= in.readFloat();
        }
		if(version<8)
			swimForce=.4f;
		else if(version==8)
			swimForce=in.readFloat();

        if(version<9)
		{
			for (int i = 0; i < N_VALUES; i++) {
				mValues[i].i = 0;
				mValues[i].a = 0f;
				mValues[i].b = 0f;
				mValues[i].c = 0f;
			}
			for (int i = 0; i < N_INTS; i++) {
				mInts[i] = 0;
			}
			for (int i = 0; i < N_FLOATS; i++) {
				mFloats[i] = 0f;
			}
			mValues[SWIM_FORCE_I].b=-1f+2f*(swimForce-VAL_MIN[SWIM_FORCE_I])/(VAL_MAX[SWIM_FORCE_I]-VAL_MIN[SWIM_FORCE_I]);
			mValues[DENSITY_VAL_I].b=-1f+2f*(density-VAL_MIN[SWIM_FORCE_I])/(VAL_MAX[SWIM_FORCE_I]-VAL_MIN[SWIM_FORCE_I]);;
			mInts[VIRUS_FROM_I]=virFrom;
			mInts[VIRUS_TO_I]=virTo;
			mFloats[CYTO_SKELETON_FLOAT_I] = 1f;
			mInts[MAX_CONNECTIONS_I] = INT_MAX[MAX_CONNECTIONS_I]-1;
		}else {
            for (int i = 0; i < N_VALUES; i++) {
                mValues[i].i = in.readShort();
	            if(version>=17)
	                mValues[i].type = in.readShort();
	            else{
		            if(mValues[i].i==-1){
			            mValues[i].i=0;
		                mValues[i].type=0;
		            }
		            else{
			            if(mValues[i].i<N_SS){
				            mValues[i].type=1;
		                }
		                else
			            {
				            mValues[i].i-=N_SS;
				            mValues[i].type=2;
			            }
		            }
	            }
                mValues[i].a = in.readFloat();
                mValues[i].b = in.readFloat();
                mValues[i].c = in.readFloat();
            }
            for (int i = 0; i < N_INTS; i++) {
                if(version==10 && i== SECRETE_TYPE){mInts[i] = 1;continue;}
	            if(version<15 && i==MAX_CONNECTIONS_I){mInts[i] = MAX_NEIGHBORS-2;continue;}
	            if(version<20 && i==TELOMERASE_LENGTH_I){mInts[i] = 0;continue;}
                mInts[i] = in.readInt();
            }
            for (int i = 0; i < N_FLOATS; i++) {
	            if(version<14 && i==CYTO_SKELETON_FLOAT_I){mFloats[i] = 1f;continue;}
	            if(version<16 && i==ADHESIN_LENGTH_FLOAT_I){mFloats[i] = 0f;continue;}

                mFloats[i] = in.readFloat();
            }
        }
	}
}
