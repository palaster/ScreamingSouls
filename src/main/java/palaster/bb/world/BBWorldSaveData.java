package palaster.bb.world;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;

public class BBWorldSaveData extends WorldSavedData {
	
	public static final String TAG_INT_BONFIRE_POS = "Bonfire";
	public static final String TAG_INT_BONFIRE = "BonfireNumber";
	public static final String TAG_TAG_DEAD = "DeadEntityTag";
	public static final String TAG_INT_DEAD = "DeadEntityNumber";

    private static final String IDENTIFIER = "BloodBankWorldSaveData";
    private List<BlockPos> bonFirePos = new ArrayList<BlockPos>();
    private List<NBTTagCompound> deadEntities = new ArrayList<NBTTagCompound>();

    public BBWorldSaveData() { super(IDENTIFIER); }

    public BBWorldSaveData(String identity) { super(identity); }

    public void addBonfire(BlockPos pos) {
        bonFirePos.add(pos);
        markDirty();
    }

    public void removeBonfire(BlockPos pos) {
        bonFirePos.remove(pos);
        markDirty();
    }

    public BlockPos getNearestBonfireToPlayer(EntityPlayer player, BlockPos current) {
        BlockPos nearest = new BlockPos(player.worldObj.getSpawnPoint());
        for(BlockPos pos: bonFirePos) {
            if(pos != null)
                if(pos.getDistance(current.getX(), current.getY(), current.getZ()) < nearest.getDistance(current.getX(), current.getY(), current.getZ()))
                    nearest = pos;
        }
        return nearest;
    }
    
    public List<NBTTagCompound> getDeadEntities() { return deadEntities; }
    
    public NBTTagCompound getDeadEntity(int numb) { return deadEntities.get(numb); }
    
    public void addDeadEntity(NBTTagCompound nbt) {
    	deadEntities.add(nbt);
    	markDirty();
    }
    
    public void removeDeadEntity(NBTTagCompound nbt) {
    	if(deadEntities.contains(nbt))
    		deadEntities.remove(nbt);
    	markDirty();
    }
    
    public void clearDeadEntities(World world) { deadEntities.clear(); }
    
    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        for(int i = 0; i < nbt.getInteger(TAG_INT_BONFIRE); i++)
            bonFirePos.add(new BlockPos(nbt.getInteger(TAG_INT_BONFIRE_POS + "X" + i), nbt.getInteger(TAG_INT_BONFIRE_POS + "Y" + i), nbt.getInteger(TAG_INT_BONFIRE_POS + "Z" + i)));
        for(int i = 0; i < nbt.getInteger(TAG_INT_DEAD); i++)
        	addDeadEntity(nbt.getCompoundTag(TAG_TAG_DEAD + i));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
        int i = 0;
        for(BlockPos bp : bonFirePos)
            if(bp != null) {
                nbt.setInteger(TAG_INT_BONFIRE_POS + "X" + i, bp.getX());
                nbt.setInteger(TAG_INT_BONFIRE_POS + "Y" + i, bp.getY());
                nbt.setInteger(TAG_INT_BONFIRE_POS + "Z" + i, bp.getZ());
                i++;
            }
        nbt.setInteger(TAG_INT_BONFIRE, i);
        int j = 0;
        for(NBTTagCompound tag : deadEntities)
        	if(tag != null) {
        		nbt.setTag(TAG_TAG_DEAD + j, tag);
        		j++;
        	} 
        nbt.setInteger(TAG_INT_DEAD, j);
        return nbt;
    }

    public static BBWorldSaveData get(World world) {
        BBWorldSaveData data = (BBWorldSaveData)world.getPerWorldStorage().getOrLoadData(BBWorldSaveData.class, IDENTIFIER);
        if(data == null) {
            data = new BBWorldSaveData();
            world.getPerWorldStorage().setData(IDENTIFIER, data);
        }
        return data;
    }
}
