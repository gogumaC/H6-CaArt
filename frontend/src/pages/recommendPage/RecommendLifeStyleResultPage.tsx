import React, { useContext, useEffect } from 'react';
import styled from 'styled-components';
import { Link } from 'react-router-dom';
import { useFetch } from '../../hooks/useFetch';
import { EstimationContext } from '../../store/Context';
import ErrorPopup from '../../components/common/ErrorPopup';
import ResultMain from '../../components/common/result/ResultMain';
import SquareButton from '../../components/common/SquareButton';
import RecommendResultCard from '../../components/recommendPage/ageAndLifeStyle/RecommendResultCard';
import { RecommendPageProps } from './RecommendPage';
import { DarkContext } from '../../hooks/useDark';
import useScrollTop from '../../hooks/useScrollTop';

export interface LifeStyleResultProps {
  palisadeImage: string;
  recommendationCard: {
    slogan: string;
    message: string;
  };
  model: {
    modelName: string;
    trim: {
      trimName: string;
      trimPrice: number;
    };
    engine: {
      engineName: string;
      enginePrice: number;
    };
    wheelDrive: {
      wheelDriveName: string;
      wheelDrivePrice: number;
    };
    bodyType: {
      bodyTypeName: string;
      bodyTypePrice: number;
    };
    modelPrice: number;
  };
  colors: {
    colorImage: string;
    isExterior: boolean;
    colorName: string;
    colorPrice: number;
    recommendationMessage: string;
    colorPreview: string;
  }[];
  options: {
    optionImage: string;
    optionName: string;
    optionPrice: number;
    recommendationMessage: string;
  }[];
  totalPrice: number;
}

function RecommendLifeStyleResultPage({
  choice,
}: Pick<RecommendPageProps, 'choice'>) {
  const { isDark } = useContext(DarkContext)!;
  const { setResult } = useContext(EstimationContext)!;

  const { data, status, error } = useFetch<LifeStyleResultProps>(
    `/personas/${choice.lifeStyle}/recommendation?age=${choice.age.code}`,
  );

  useScrollTop();

  useEffect(() => {
    if (data) {
      setResult(data);
    }
  }, [data]);

  if (status === 'loading') {
    return <div></div>;
  } else if (status === 'error') {
    console.error(error);
    return <ErrorPopup></ErrorPopup>;
  }
  if (data === null) return <div></div>;

  return (
    <RecommendLifeStyleResultPageBox>
      <RecommendLifeStyleResultPageCarImgBox $isDark={isDark}>
        <RecommendResultCard
          palisadeImage={data.palisadeImage}
          model={data.model}
          recommendationCard={data.recommendationCard}
        ></RecommendResultCard>
      </RecommendLifeStyleResultPageCarImgBox>
      <ResultMain></ResultMain>
      <RecommendLifeStyleResultPageBtnBox>
        <Link to="/estimate/trim">
          <SquareButton size="m" color="grey-50" $bg="grey-1000" $border>
            커스텀하기
          </SquareButton>
        </Link>
        <Link to="/result">
          <SquareButton size="m" color="grey-1000" $bg="primary-blue">
            빠른 견적내기
          </SquareButton>
        </Link>
      </RecommendLifeStyleResultPageBtnBox>
    </RecommendLifeStyleResultPageBox>
  );
}

const RecommendLifeStyleResultPageBox = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
`;

const RecommendLifeStyleResultPageCarImgBox = styled.div<{ $isDark: boolean }>`
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
  height: 485px;
  margin-bottom: 56px;
  background: ${props =>
    props.$isDark
      ? 'linear-gradient(180deg, #1a1a1a 0%, #2c2c2c 100%)'
      : 'linear-gradient(180deg, #a2b1d3 0%, #edf2fe 100%)'};
`;

const RecommendLifeStyleResultPageBtnBox = styled.div`
  display: flex;
  gap: 12px;
  margin-top: 60px;
  margin-bottom: 36px;
`;

export default RecommendLifeStyleResultPage;
