import React, { useState } from 'react';
import { styled } from 'styled-components';
import { Header } from '../../components/common/header/Header';
import SquareButton from '../../components/common/SquareButton';
import { PageNum } from '../../components/recommendPage/ageAndLifeStyle/PageNum';
import { LifeStyleCard } from '../../components/recommendPage/lifeStyleCard/LifeStyleCard';
import { LifeStylePeekModal } from '../../components/recommendPage/lifeStylePeek/LifeStylePeekModal';

function RecomendLifeStylePage() {
  const [openedModalNum, setOpenedModalNum] = useState(0);
  return (
    <RecomendLifeStylePageBox>
      <Header size="minimal" page={1}></Header>
      <RecomendLifeStyleMain>
        <RecomendLifeStylePageTitle>
          <RecomendLifeStylePageTitleText className="text-grey-0">
            <span className="head-regular-22">유사한 </span>
            <span className="head-medium-22">라이프스타일</span>
            <span className="head-regular-22">
              을 선택하면
              <br />
              차량 조합을 추천해 드려요
            </span>
          </RecomendLifeStylePageTitleText>
          <PageNum>2/2</PageNum>
        </RecomendLifeStylePageTitle>
        <RecomendLifeStylePageExit
          className="body-medium-14 text-secondary-active-blue"
          onClick={() => {}}
        >
          원하는 라이프사티일이 없다면?
        </RecomendLifeStylePageExit>
        <RecomendLifeStyleCardBox>
          <LifeStyleCard
            id={data.id}
            tag={data.tag}
            text={data.text}
            imgSrc={data.imgSrc}
            setOpenedModalNum={setOpenedModalNum}
          ></LifeStyleCard>
          <LifeStyleCard
            id={data.id}
            tag={data.tag}
            text={data.text}
            imgSrc={data.imgSrc}
            setOpenedModalNum={setOpenedModalNum}
          ></LifeStyleCard>
          <LifeStyleCard
            id={data.id}
            tag={data.tag}
            text={data.text}
            imgSrc={data.imgSrc}
            setOpenedModalNum={setOpenedModalNum}
          ></LifeStyleCard>
          <LifeStyleCard
            id={data.id}
            tag={data.tag}
            text={data.text}
            imgSrc={data.imgSrc}
            setOpenedModalNum={setOpenedModalNum}
          ></LifeStyleCard>
        </RecomendLifeStyleCardBox>
        <SquareButton size="xl" color="grey-1000" bg="primary-blue">
          선택 완료
        </SquareButton>
      </RecomendLifeStyleMain>
      {openedModalNum !== 0 && (
        <LifeStylePeekModal
          profile={modalData.profile}
          tag={modalData.tag}
          title={modalData.title}
          imgSrc={modalData.imgSrc}
          setOpenedModalNum={setOpenedModalNum}
        ></LifeStylePeekModal>
      )}
    </RecomendLifeStylePageBox>
  );
}

const RecomendLifeStylePageBox = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const RecomendLifeStyleMain = styled.div`
  width: 608px;
  height: 740px;
  margin-top: 48px;
`;

const RecomendLifeStylePageTitle = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 16px;
`;

const RecomendLifeStylePageTitleText = styled.div``;

const RecomendLifeStylePageExit = styled.div`
  line-height: 16px;
  letter-spacing: -0.07px;
  text-decoration: underline;
  margin-bottom: 52px;
  cursor: pointer;
`;

const RecomendLifeStyleCardBox = styled.div`
  display: flex;
  flex-wrap: wrap;
  gap: 16px;
  align-content: space-between;
  height: 420px;
  margin-bottom: 40px;
`;

export { RecomendLifeStylePage };

const data = {
  id: 1,
  tag: ['#주행안전', '#사용편의'],
  text: '가족과 함께 타서\n안전을 중시해요.',
  imgSrc: 'https://picsum.photos/200/300',
};

const modalData = {
  profile: {
    imgSrc: 'https://picsum.photos/200/300',
    name: '김현대',
    text: '두 아이의 엄마',
    talk: '“우리 아이들과 함께 타는 차는 항상\n안전해야 한다고 생각해요."',
  },
  tag: ['#주행안전', '#사용편의'],
  title: '가족과 함께 타서 안전을 중시해요.',
  imgSrc: 'https://picsum.photos/200/300',
};
