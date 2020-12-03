package telran.propets.dispatcher.domain.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import telran.propets.dispatcher.dto.Address;
import telran.propets.dispatcher.dto.KafkaReqType;
import telran.propets.dispatcher.dto.LocationDto;
import telran.propets.dispatcher.dto.LostFoundKafkaDto;

@Document(indexName = "lostfoundposts")
public class PostEntity {
	
	@Id
	String id;
	KafkaReqType kafkaReqType;
	boolean typePost;
	String userLogin;
	String userName;
	String avatar;
	String datePost;
	String type;
	String sex;
	String breed;
	String[] tags;
	String[] photos;
	Address address;
	LocationDto location;
	
	public PostEntity() {}

	public PostEntity(String id, KafkaReqType kafkaReqType, boolean typePost, String userLogin, String userName,
			String avatar, String datePost, String type, String sex, String breed, String[] tags, String[] photos,
			Address address, LocationDto location) {
		super();
		this.id = id;
		this.kafkaReqType = kafkaReqType;
		this.typePost = typePost;
		this.userLogin = userLogin;
		this.userName = userName;
		this.avatar = avatar;
		this.datePost = datePost;
		this.type = type;
		this.sex = sex;
		this.breed = breed;
		this.tags = tags;
		this.photos = photos;
		this.address = address;
		this.location = location;
	}

	public PostEntity(LostFoundKafkaDto dto) {
		super();
		this.id = dto.id;
		this.kafkaReqType = dto.kafkaReqType;
		this.typePost = dto.typePost;
		this.userLogin = dto.userLogin;
		this.userName = dto.userName;
		this.avatar = dto.avatar;
		this.datePost = dto.datePost;
		this.type = dto.type;
		this.sex = dto.sex;
		this.breed = dto.breed;
		this.tags = dto.tags;
		this.photos = dto.photos;
		this.address = dto.address;
		this.location = dto.location;
	}

	public String getId() {
		return id;
	}

	public KafkaReqType getKafkaReqType() {
		return kafkaReqType;
	}

	public boolean isTypePost() {
		return typePost;
	}

	public String getUserLogin() {
		return userLogin;
	}

	public String getUserName() {
		return userName;
	}

	public String getAvatar() {
		return avatar;
	}

	public String getDatePost() {
		return datePost;
	}

	public String getType() {
		return type;
	}

	public String getSex() {
		return sex;
	}

	public String getBreed() {
		return breed;
	}

	public String[] getTags() {
		return tags;
	}

	public String[] getPhotos() {
		return photos;
	}

	public Address getAddress() {
		return address;
	}

	public LocationDto getLocation() {
		return location;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setKafkaReqType(KafkaReqType kafkaReqType) {
		this.kafkaReqType = kafkaReqType;
	}

	public void setTypePost(boolean typePost) {
		this.typePost = typePost;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public void setDatePost(String datePost) {
		this.datePost = datePost;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public void setPhotos(String[] photos) {
		this.photos = photos;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void setLocation(LocationDto location) {
		this.location = location;
	}
	
}
