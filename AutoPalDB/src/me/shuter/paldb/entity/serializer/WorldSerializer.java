package me.shuter.paldb.entity.serializer;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import me.shuter.paldb.entity.World;
import me.shuter.paldb.utils.WeightHelper;

import com.linkedin.paldb.api.Serializer;

@SuppressWarnings("serial")
public class WorldSerializer extends WeightHelper implements Serializer<World> {

	@Override
	public void write(DataOutput dataOutput, World input) throws IOException {
		dataOutput.writeInt(input.getId());
		dataOutput.writeUTF(input.getPlaceName());
		dataOutput.writeUTF(input.getAddress());
	}

	@Override
	public World read(DataInput dataInput) throws IOException {
		World output = new World();
		output.setId(dataInput.readInt());
		output.setPlaceName(dataInput.readUTF());
		output.setAddress(dataInput.readUTF());
		return output;
	}

	@Override
	public int getWeight(World instance) {
		int weight = 0;
		weight += getValueWeight(instance.getId());
		weight += getValueWeight(instance.getPlaceName());
		weight += getValueWeight(instance.getAddress());
		return weight;
	}
}
