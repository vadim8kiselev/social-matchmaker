package com.kiselev.matchmaker.api.network.vk.converter;

import com.google.common.collect.Lists;
import com.kiselev.matchmaker.api.EntityConverter;
import com.kiselev.matchmaker.api.model.Group;
import com.kiselev.matchmaker.api.model.Post;
import com.kiselev.matchmaker.api.model.User;
import com.vk.api.sdk.objects.groups.ContactsItem;
import com.vk.api.sdk.objects.groups.GroupFull;
import com.vk.api.sdk.objects.groups.LinksItem;
import com.vk.api.sdk.objects.photos.Photo;
import com.vk.api.sdk.objects.polls.Answer;
import com.vk.api.sdk.objects.users.*;
import com.vk.api.sdk.objects.wall.WallpostAttachment;
import com.vk.api.sdk.objects.wall.WallpostAttachmentType;
import com.vk.api.sdk.objects.wall.WallpostFull;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author: Vadim Kiselev
 * @date: 24.01.2018
 */
public class VKEntityConverter implements EntityConverter<UserFull, WallpostFull, GroupFull> {

    @Override
    public User convertUser(UserFull externalEntity) {
        return User.builder()
                .id(externalEntity.getId().toString())
                .firstName(externalEntity.getFirstName())
                .lastName(externalEntity.getLastName())
                .screenName(nonNull(externalEntity.getScreenName()))
                .sex(nonNull(externalEntity.getSex()))
                .birthday(nonNull(externalEntity.getBdate()))
                .online(String.valueOf(externalEntity.isOnline() || externalEntity.isOnlineMobile()))
                .photoLink(externalEntity.getPhotoMaxOrig())

                .city(externalEntity.getCity() != null ? externalEntity.getCity().getTitle() : "")
                .homeTown(nonNull(externalEntity.getHomeTown()))
                .country(externalEntity.getCountry() != null ? externalEntity.getCountry().getTitle() : "")

                .mobilePhone(nonNull(externalEntity.getMobilePhone()))
                .homePhone(nonNull(externalEntity.getHomePhone()))

                .skype(nonNull(externalEntity.getSkype()))
                .facebook(nonNull(externalEntity.getFacebook()))
                .twitter(nonNull(externalEntity.getTwitter()))
                .livejournal(nonNull(externalEntity.getLivejournal()))
                .instagram(nonNull(externalEntity.getInstagram()))

                .status(nonNull(externalEntity.getStatus()))
                .activity(nonNull(externalEntity.getActivity()))
                .lastSeen(externalEntity.getLastSeen() != null
                        ? new Date(externalEntity.getLastSeen().getTime() * 1000L).toString()
                        : "")

                .career(listToString(nonNull(externalEntity.getCareer())))
                .military(listToString(nonNull(externalEntity.getMilitary())))
                .university(listToString(nonNull(externalEntity.getUniversities())))
                .schools(listToString(nonNull(externalEntity.getSchools())))

                .relation(nonNull(externalEntity.getRelation()))
                .relationPartner(externalEntity.getRelationPartner() != null
                        ? externalEntity.getRelationPartner().getFirstName() + " " + externalEntity.getRelationPartner().getLastName()
                        : "")

                .interests(nonNull(externalEntity.getInterests()))
                .music(nonNull(externalEntity.getMusic()))
                .activities(nonNull(externalEntity.getActivities()))
                .movies(nonNull(externalEntity.getMovies()))
                .tv(nonNull(externalEntity.getTv()))
                .books(nonNull(externalEntity.getBooks()))
                .games(nonNull(externalEntity.getGames()))
                .about(nonNull(externalEntity.getAbout()))
                .quotes(nonNull(externalEntity.getQuotes()))

                .political(externalEntity.getPersonal() != null ? nonNull(externalEntity.getPersonal().getPolitical()) : "")
                .languages(externalEntity.getPersonal() != null ? listToString(nonNull(externalEntity.getPersonal().getLangs())) : "")
                .religion(externalEntity.getPersonal() != null ? nonNull(externalEntity.getPersonal().getReligion()) : "")
                .inspiredBy(externalEntity.getPersonal() != null ? nonNull(externalEntity.getPersonal().getInspiredBy()) : "")
                .peopleMain(externalEntity.getPersonal() != null ? nonNull(externalEntity.getPersonal().getPeopleMain()) : "")
                .lifeMain(externalEntity.getPersonal() != null ? nonNull(externalEntity.getPersonal().getLifeMain()) : "")
                .smoking(externalEntity.getPersonal() != null ? nonNull(externalEntity.getPersonal().getSmoking()) : "")
                .alcohol(externalEntity.getPersonal() != null ? nonNull(externalEntity.getPersonal().getAlcohol()) : "")

                .deactivated(nonNull(externalEntity.getDeactivated()))
                .build();
    }

    @Override
    public Post convertPost(WallpostFull externalPost) {
        return Post.builder()
                .id(externalPost.getId().toString())
                .fromId(externalPost.getFromId().toString())
                .ownerId(externalPost.getOwnerId().toString())
                .date(externalPost.getDate() != null
                        ? new Date(externalPost.getDate() * 1000L).toString()
                        : "")
                .text(externalPost.getText())

                .likesCount(externalPost.getLikes() != null ? externalPost.getLikes().getCount().toString() : "")
                .sharesCount(externalPost.getReposts() != null ? externalPost.getReposts().getCount().toString() : "")
                .viewsCount(externalPost.getViews() != null ? externalPost.getViews().getCount().toString() : "")
                .commentsCount(externalPost.getComments() != null ? externalPost.getComments().getCount().toString() : "")

                .geo(nonNull(externalPost.getGeo()))
                .type(externalPost.getPostType().getValue())
                .attachments(listToString(nonNull(externalPost.getAttachments())))
                .platform(nonNull(externalPost.getPostSource().getPlatform()))
                .build();
    }

    @Override
    public Group convertGroup(GroupFull externalGroup) {
        return Group.builder()
                .id(externalGroup.getId())
                .name(externalGroup.getName())
                .screenName(nonNull(externalGroup.getScreenName()))
                .status(nonNull(externalGroup.getStatus()))
                .description(nonNull(externalGroup.getDescription()))

                .type(nonNull(externalGroup.getType()))
                .verified(String.valueOf(externalGroup.isVerified()))
                .isClosed(nonNull(externalGroup.getIsClosed()))
                .ageLimits(nonNull(externalGroup.getAgeLimits()))
                .subscribersCount(nonNull(externalGroup.getMembersCount()))

                .city(nonNull(externalGroup.getCity()))
                .country(nonNull(externalGroup.getCountry()))

                .photoLink(externalGroup.getPhoto200())

                .links(listToString(nonNull(externalGroup.getLinks())))
                .contacts(listToString(nonNull(externalGroup.getContacts())))
                .site(nonNull(externalGroup.getSite()))

                .deactivated(nonNull(externalGroup.getDeactivated()))
                .build();
    }

    private String nonNull(String data) {
        return data != null ? data : "";
    }

    private <ExternalPojo> List<ExternalPojo> nonNull(List<ExternalPojo> data) {
        return data != null ? data : Lists.newArrayList();
    }

    private String nonNull(Object object) {
        return object != null ? object.toString() : "";
    }

    private String nonNull(Enum enumObject) {
        return enumObject != null ? enumObject.name() : "";
    }

    private <ExternalPojo> String listToString(List<ExternalPojo> data) {
        return String.join("; ", data.stream()
                .map(this::pojoToString)
                .filter(Objects::nonNull)
                .collect(Collectors.toList()));
    }

    private String pojoToString(Object pojo) {
        if (pojo instanceof Career) {
            return careerToString((Career) pojo);
        } else if (pojo instanceof Military) {
            return militaryToString((Military) pojo);
        } else if (pojo instanceof University) {
            return universityToString((University) pojo);
        } else if (pojo instanceof School) {
            return schoolToString((School) pojo);
        } else if (pojo instanceof WallpostAttachment) {
            return postAttachmentToString((WallpostAttachment) pojo);
        } else if (pojo instanceof LinksItem) {
            return linkToString((LinksItem) pojo);
        } else if (pojo instanceof ContactsItem) {
            return contactToString((ContactsItem) pojo);
        } else {
            return pojo.toString();
        }
    }

    private String careerToString(Career career) {
        if (career.getCompany() != null) {
            String company = career.getCompany() + ", ";
            String position = career.getPosition() != null ? career.getPosition() + ", " : "";

            Integer from = career.getFrom();
            Integer until = career.getUntil();
            String date = from != null
                    ? "(" + from + " - " + (until != null ? until : "Till now") + ")"
                    : "";

            return company + position + date;
        }
        return null;
    }

    private String militaryToString(Military military) {
        if (military.getUnit() != null) {
            String name = military.getUnit() + " ";

            Integer from = military.getFrom();
            Integer until = military.getUntil();
            String date = from != null
                    ? "(" + from + " - " + (until != null ? until : "Till now") + ")"
                    : "";

            return name + date;
        }
        return null;
    }

    private String universityToString(University university) {
        if (university.getName() != null) {
            String name = university.getName() + ", ";
            String faculty = university.getFacultyName() != null ? university.getFacultyName() + ", " : "";
            String chair = university.getChairName() != null ? university.getChairName() + ", " : "";
            String educationStatus = university.getEducationStatus() != null ? university.getEducationStatus() + ", " : "";
            String educationForm = university.getEducationForm() != null ? university.getEducationForm() + ", " : "";
            String date = university.getGraduation() != null ? "#" + university.getGraduation().toString() : "";

            return name + faculty + chair + educationStatus + educationForm + date;
        }
        return null;
    }

    private String schoolToString(School school) {
        if (school.getName() != null) {
            String name = school.getName() + ", ";
            String type = school.getTypeStr() != null ? school.getTypeStr() + ", " : "";

            Integer from = school.getYearFrom();
            Integer to = school.getYearTo() != null
                    ? school.getYearTo()
                    : school.getYearGraduated();

            String date = from != null
                    ? "(" + from + " - " + (to != null ? to : "Till now") + ")"
                    : "";

            return type + name + date;
        }
        return null;
    }

    private String postAttachmentToString(WallpostAttachment postAttachment) {
        WallpostAttachmentType type = postAttachment.getType();
        String attachment = "";

        switch (type) {
            case APP:
                attachment = postAttachment.getApp().getName();
                break;
            case DOC:
                attachment = postAttachment.getDoc().getTitle();
                break;
            case LINK:
                attachment = postAttachment.getLink().getUrl();
                break;
            case NOTE:
                attachment = postAttachment.getNote().getTitle();
                break;
            case PAGE:
                attachment = postAttachment.getPage().getTitle();
                break;
            case POLL:
                attachment = postAttachment.getPoll().getQuestion()
                        + " [" + String.join("; ", postAttachment.getPoll().getAnswers().stream().map(Answer::getText).collect(Collectors.toList())) + "]";
                break;
            case ALBUM:
                attachment = postAttachment.getAlbum().getTitle();
                break;
            case AUDIO:
                attachment = postAttachment.getAudio().getArtist() + " - " + postAttachment.getAudio().getTitle();
                break;
            case PHOTO:
                attachment = photoToString(postAttachment.getPhoto());
                break;
            case VIDEO:
                attachment = postAttachment.getVideo().getTitle();
                break;
            case MARKET:
                attachment = postAttachment.getMarket().getTitle();
                break;
            case GRAFFITI:
                attachment = postAttachment.getGraffiti().getPhoto586();
                break;
            case PHOTOS_LIST:
                attachment = String.join(", ", postAttachment.getPhotosList());
                break;
            case POSTED_PHOTO:
                attachment = postAttachment.getPostedPhoto().getPhoto604();
                break;
            case MARKET_MARKET_ALBUM:
                attachment = postAttachment.getMarketMarketAlbum().getTitle();
                break;
        }

        return type.name() + " - " + attachment;
    }

    private String linkToString(LinksItem link) {
        return link.getName() != null && link.getUrl() != null ? link.getName() + " - " + link.getUrl() : null;
    }

    private String contactToString(ContactsItem contact) {
        Integer userId = contact.getUserId();
        String email = contact.getEmail() != null ? " - " + contact.getEmail() : "";
        String phone = contact.getPhone() != null ? " - " + contact.getPhone() : "";
        return userId != null ? userId + email + phone : null;
    }

    private String photoToString(Photo photo) {
        if (photo.getPhoto2560() != null) {
            return photo.getPhoto2560();
        }
        if (photo.getPhoto1280() != null) {
            return photo.getPhoto1280();
        }
        if (photo.getPhoto807() != null) {
            return photo.getPhoto807();
        }
        if (photo.getPhoto604() != null) {
            return photo.getPhoto604();
        }
        if (photo.getPhoto130() != null) {
            return photo.getPhoto130();
        }
        return nonNull(photo.getPhoto75());
    }
}
